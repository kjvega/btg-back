package com.btg.fondos.application.service;

import com.btg.fondos.application.port.in.SubscribeUseCase;
import com.btg.fondos.application.port.in.CancelUseCase;
import com.btg.fondos.application.port.in.TransactionHistoryUseCase;
import com.btg.fondos.application.port.out.ClientRepositoryPort;
import com.btg.fondos.application.port.out.FundRepositoryPort;
import com.btg.fondos.application.port.out.TransactionRepositoryPort;
import com.btg.fondos.domain.Client;
import com.btg.fondos.domain.Fund;
import com.btg.fondos.domain.SubscribedFund;
import com.btg.fondos.domain.Transaction;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FundService implements SubscribeUseCase, CancelUseCase, TransactionHistoryUseCase {

    private final ClientRepositoryPort clientRepository;
    private final FundRepositoryPort fundRepository;
    private final TransactionRepositoryPort transactionRepository;

    public FundService(ClientRepositoryPort clientRepository,
                       FundRepositoryPort fundRepository,
                       TransactionRepositoryPort transactionRepository) {
        this.clientRepository = clientRepository;
        this.fundRepository = fundRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction subscribe(String clientId, String fundId, double amount, String notificationType) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        Fund fund = fundRepository.findById(fundId)
                .orElseThrow(() -> new RuntimeException("Fund not found"));

        if (amount < fund.getMinimumAmount()) {
            throw new RuntimeException("Minimum amount to subscribe is " + fund.getMinimumAmount());
        }

        if (client.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance to subscribe to fund " + fund.getName());
        }

        Date now = new Date();

        // Deduct balance
        client.setBalance(client.getBalance() - amount);

        // Add fund to client subscriptions
        SubscribedFund subscribedFund = new SubscribedFund(
                fund.getId(),
                fund.getName(),
                amount,
                now
        );
        client.getSubscribedFunds().add(subscribedFund);

        clientRepository.save(client);

        // Create transaction
        Transaction transaction = new Transaction(
                UUID.randomUUID().toString(),
                "SUBSCRIPTION",
                fund.getName(),
                amount,
                now,
                clientId
        );

        transactionRepository.save(transaction);

        // TODO: mover a NotificationPort en lugar de System.out.println
        System.out.println("Notification sent via " + notificationType + ": subscribed to " + fund.getName());

        return transaction;
    }

    @Override
    public Transaction cancel(String clientId, String fundId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        Optional<SubscribedFund> subscribedFundOpt = client.getSubscribedFunds().stream()
                .filter(sf -> sf.getFundId().equals(fundId))
                .findFirst();

        if (subscribedFundOpt.isEmpty()) {
            throw new RuntimeException("Client is not subscribed to this fund");
        }

        SubscribedFund subscribedFund = subscribedFundOpt.get();
        Date now = new Date();

        // Refund balance
        client.setBalance(client.getBalance() + subscribedFund.getInvestedAmount());

        // Remove fund
        client.getSubscribedFunds().remove(subscribedFund);
        clientRepository.save(client);

        // Create transaction
        Transaction transaction = new Transaction(
                UUID.randomUUID().toString(),
                "CANCELLATION",
                subscribedFund.getName(),
                subscribedFund.getInvestedAmount(),
                now,
                clientId
        );

        transactionRepository.save(transaction);

        return transaction;
    }

    @Override
    public List<Transaction> getHistory(String clientId) {
        return transactionRepository.findByClientId(clientId);
    }
}
