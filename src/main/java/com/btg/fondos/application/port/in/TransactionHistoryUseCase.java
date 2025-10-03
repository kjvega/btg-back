package com.btg.fondos.application.port.in;

import com.btg.fondos.domain.Transaction;

import java.util.List;

public interface TransactionHistoryUseCase {
    List<Transaction> getHistory(String clientId);
}
