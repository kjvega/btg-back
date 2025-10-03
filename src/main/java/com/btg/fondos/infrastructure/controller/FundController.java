package com.btg.fondos.infrastructure.controller;

import com.btg.fondos.application.port.in.SubscribeUseCase;
import com.btg.fondos.application.port.in.CancelUseCase;
import com.btg.fondos.application.port.in.TransactionHistoryUseCase;
import com.btg.fondos.domain.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.btg.fondos.infrastructure.controller.dto.SubscribeRequest;
import com.btg.fondos.infrastructure.controller.dto.CancelRequest;


import java.util.List;

@RestController
@RequestMapping("/funds")
@RequiredArgsConstructor
public class FundController {

    private final SubscribeUseCase subscribeUseCase;
    private final CancelUseCase cancelUseCase;
    private final TransactionHistoryUseCase transactionHistoryUseCase;

    @PostMapping("/subscribe")
    public ResponseEntity<Transaction> subscribe(@RequestBody SubscribeRequest request) {
        Transaction transaction = subscribeUseCase.subscribe(
                request.getClientId(),
                request.getFundId(),
                request.getAmount(),
                request.getNotificationType()
        );
        return ResponseEntity.ok(transaction);
    }

    @PostMapping("/cancel")
    public ResponseEntity<Transaction> cancel(@RequestBody CancelRequest request) {
        Transaction transaction = cancelUseCase.cancel(
                request.getClientId(),
                request.getFundId()
        );
        return ResponseEntity.ok(transaction);
    }

    @GetMapping("/history/{clientId}")
    public ResponseEntity<List<Transaction>> history(@PathVariable String clientId) {
        return ResponseEntity.ok(transactionHistoryUseCase.getHistory(clientId));
    }
}
