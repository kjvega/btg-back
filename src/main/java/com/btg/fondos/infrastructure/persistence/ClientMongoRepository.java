package com.btg.fondos.infrastructure.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientMongoRepository extends MongoRepository<ClientDocument, String> {}
