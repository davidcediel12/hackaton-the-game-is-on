package com.hackathon.bankingapp.controller.account;


import com.hackathon.bankingapp.dto.request.account.TransactionRequest;
import com.hackathon.bankingapp.dto.response.ReducedGenericResponse;
import com.hackathon.bankingapp.services.transaction.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

    private final TransactionService transactionService;


    @PostMapping("/deposit")
    public ResponseEntity<ReducedGenericResponse> depositMoney(@Valid @RequestBody
                                                               TransactionRequest transactionRequest) {

        transactionService.depositMoney(transactionRequest);
        return ResponseEntity.ok(new ReducedGenericResponse("Cash deposited successfully"));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<ReducedGenericResponse> withdrawMoney(@Valid @RequestBody
                                                                TransactionRequest transactionRequest) {

        transactionService.withdrawMoney(transactionRequest);
        return ResponseEntity.ok(new ReducedGenericResponse("Cash withdrawn successfully"));
    }
}
