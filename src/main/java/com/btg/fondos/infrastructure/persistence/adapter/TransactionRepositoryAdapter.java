package com.btg.fondos.infrastructure.persistence.adapter;

import com.btg.fondos.application.port.out.TransactionRepositoryPort;
import com.btg.fondos.domain.Transaction;
import com.btg.fondos.infrastructure.persistence.TransactionDocument;
import com.btg.fondos.infrastructure.persistence.TransactionMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TransactionRepositoryAdapter implements TransactionRepositoryPort {

    private final TransactionMongoRepository transactionMongoRepository;

    @Override
    public Transaction save(Transaction tx) {
        return transactionMongoRepository.save(TransactionDocument.fromDomain(tx)).toDomain();
    }

    @Override
    public List<Transaction> findByClientId(String clientId) {
        return transactionMongoRepository.findByClientId(clientId)
                .stream().map(TransactionDocument::toDomain).toList();
    }
}
