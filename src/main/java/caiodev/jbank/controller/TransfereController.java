package caiodev.jbank.controller;

import caiodev.jbank.controller.dto.TransferMoneyDto;
import caiodev.jbank.entities.Transfer;
import caiodev.jbank.service.TransferService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/transfers")
public class TransfereController {

    private final TransferService transferService;

    public TransfereController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity<Void> transfer(@RequestBody @Valid TransferMoneyDto dto){

        transferService.transferMoney(dto);



        return ResponseEntity.ok().build();
    }




}
