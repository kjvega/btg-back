package com.btg.fondos.application.port.out;

import com.btg.fondos.domain.Transaction;

import java.util.List;

public interface TransactionRepositoryPort {
    Transaction save(Transaction transaction);
    List<Transaction> findByClientId(String clientId);
}
