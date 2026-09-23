package caiodev.jbank.service;

import caiodev.jbank.controller.dto.TransferMoneyDto;
import caiodev.jbank.entities.Transfer;
import caiodev.jbank.entities.Wallet;
import caiodev.jbank.exception.TransferException;
import caiodev.jbank.exception.WalletNotFoundException;
import caiodev.jbank.repository.TrasferRepository;
import caiodev.jbank.repository.WalletRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class TransferService {
    private final WalletRepository walletRepository;
    private final TrasferRepository transferRepository;

    public TransferService(WalletRepository walletRepository, TrasferRepository transferRepository) {
        this.walletRepository = walletRepository;
        this.transferRepository = transferRepository;
    }

    @Transactional
    public void transferMoney(@Valid TransferMoneyDto dto) {

        var sender = walletRepository.findById(dto.sender())
                .orElseThrow(() -> new WalletNotFoundException("sender does not exist"));

        var receiver = walletRepository.findById(dto.receiver())
                .orElseThrow(() -> new WalletNotFoundException("receiver does not exist"));

        if (sender.getBalance().compareTo(dto.value()) == -1) {
            throw new TransferException(
                    "insufficient balance. You current balance is $" + sender.getBalance());

        }

        persistTransfer(dto, receiver, sender);
        updateWallet(dto, sender, receiver);
    }

    private void updateWallet(TransferMoneyDto dto, Wallet sender, Wallet receiver) {
        sender.setBalance(sender.getBalance().subtract(dto.value()));
        receiver.setBalance(receiver.getBalance().add(dto.value()));
        walletRepository.save(sender);
        walletRepository.save(receiver);
    }

    private void persistTransfer(TransferMoneyDto dto, Wallet receiver, Wallet sender) {
        var transfer = new Transfer();
        transfer.setReceiver(receiver);
        transfer.setSender(sender);
        transfer.setTransferValue(dto.value());
        transfer.setTransferDateTime(LocalDateTime.now());
        transferRepository.save(transfer);
    }
}
