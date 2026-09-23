package caiodev.jbank.service;

import caiodev.jbank.controller.dto.*;
import caiodev.jbank.entities.Deposits;
import caiodev.jbank.entities.Wallet;
import caiodev.jbank.exception.DeleteWalletException;
import caiodev.jbank.exception.StatementException;
import caiodev.jbank.exception.WalletDataAlreadyExistException;
import caiodev.jbank.exception.WalletNotFoundException;
import caiodev.jbank.repository.DepositRepository;
import caiodev.jbank.repository.WalletRepository;
import caiodev.jbank.repository.dto.StatementView;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class WalletService {

    private final WalletRepository walletRepository;
    private final DepositRepository depositRepository;


    public WalletService(WalletRepository walletRepository, DepositRepository depositRepository) {
        this.walletRepository = walletRepository;
        this.depositRepository = depositRepository;
    }

    public Wallet createWallet(CreateWalletDto dto) {

        var walletDb = walletRepository.findByCpfOrEmail(dto.cpf(), dto.email());

        if (walletDb.isPresent()) {
            throw new WalletDataAlreadyExistException("Cpf or email already exist");

        }

        var wallet = new Wallet();
        wallet.setBalance(BigDecimal.ZERO);
        wallet.setName(dto.name());
        wallet.setCpf(dto.cpf());
        wallet.setEmail(dto.email());


        return walletRepository.save(wallet);


    }

    public boolean deleteWallet(UUID walletId) {

        var wallet = walletRepository.findById(walletId);

        if (wallet.isPresent()) {
            if (wallet.get().getBalance().compareTo(BigDecimal.ZERO) != 0) {
                throw new DeleteWalletException(
                        "the balance is not zero. The Current amount is $" + wallet.get().getBalance());

            }
            walletRepository.deleteById(walletId);
        }

        return wallet.isPresent();
    }

    @Transactional
    public void depositMoney(UUID walletId,
                             @Valid DepositMoneyDto dto,
                             String ipAdress) {

        var wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new WalletNotFoundException("there is no walllet with this id"));

        var deposity = new Deposits();
        deposity.setWallet(wallet);
        deposity.setDepositVallue(dto.value());
        deposity.setDepositDateTime(LocalDateTime.now());
        deposity.setIpAdress(ipAdress);

        depositRepository.save(deposity);

        wallet.setBalance(wallet.getBalance().add(dto.value()));

        walletRepository.save(wallet);


    }

    public StatementDto getStatements(UUID walletId, Integer page, Integer pageSize) {

        var wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new WalletNotFoundException("there is no wallet with this id"));

        var pageRequest = PageRequest.of(page, pageSize, Sort.Direction.DESC, "statement_date_time");

        var statements = walletRepository.findStatements(walletId.toString(), pageRequest)
                .map(view -> mapToDto(walletId, view));

        return new StatementDto(
                new WalletDto(wallet.getWalletId(), wallet.getCpf(), wallet.getName(), wallet.getEmail(), wallet.getBalance()),
                    statements.getContent(),
                new PaginationDto(statements.getNumber(), statements.getSize(), statements.getTotalElements(), statements.getTotalPages())
        );
    }

    private StatementsIntemDto mapToDto(UUID walletId, StatementView view) {
        var type = view.getType();

        if ("deposit".equalsIgnoreCase(type)) {
            return mapToDeposit(view);
        }

        if ("transfer".equalsIgnoreCase(type)) {
            var currentWallet = walletId.toString();

            if (currentWallet.equalsIgnoreCase(view.getWalletSender())) {
                return mapWhenTransferSent(walletId, view);
            }

            if (currentWallet.equalsIgnoreCase(view.getWalletReceiver())) {
                return mapWhenTransferReceived(walletId, view);
            }
        }

        throw new StatementException("Invalid statement scenario: " + type);
    }
    private StatementsIntemDto mapWhenTransferReceived(UUID walletId, StatementView view) {
        return  new StatementsIntemDto(
                view.getStatementId(),
                view.getType(),
                "money received from " + view.getWalletSender(),
                view.getStatementValue(),
                view.getStatementDateTime(),
                StatementOperation.CREDIT

        );
    }


    private StatementsIntemDto mapWhenTransferSent(UUID walletId, StatementView view) {
        return  new StatementsIntemDto(
                view.getStatementId(),
                view.getType(),
                "money sent to " + view.getWalletReceiver(),
                view.getStatementValue(),
                view.getStatementDateTime(),
                StatementOperation.DEBIT

        );
    }

    private StatementsIntemDto mapToDeposit(StatementView view) {
        return new StatementsIntemDto(
                view.getStatementId(),
                view.getType(),
                "money deposit",
                view.getStatementValue(),
                view.getStatementDateTime(),
                StatementOperation.CREDIT
        );
    }
}


