package caiodev.jbank.service;

import caiodev.jbank.controller.dto.CreateWalletDto;
import caiodev.jbank.controller.dto.DepositMoneyDto;
import caiodev.jbank.entities.Wallet;
import caiodev.jbank.exception.DeleteWalletException;
import caiodev.jbank.exception.WalletDataAlreadyExistException;
import caiodev.jbank.repository.WalletRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet createWallet(CreateWalletDto dto) {

        var walletDb =  walletRepository.findByCpfOrEmail(dto.cpf(),dto.email());

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

    public void depositMoney(UUID walletId, @Valid DepositMoneyDto dto) {


    }
}
