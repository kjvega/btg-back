package com.btg.fondos.infrastructure.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransactionMongoRepository extends MongoRepository<TransactionDocument, String> {
    List<TransactionDocument> findByClientId(String clientId);
}