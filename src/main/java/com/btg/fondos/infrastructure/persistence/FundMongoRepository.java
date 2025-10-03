package com.btg.fondos.infrastructure.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface FundMongoRepository extends MongoRepository<FundDocument, String> {}
