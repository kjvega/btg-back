package com.btg.fondos.infrastructure.persistence.adapter;

import com.btg.fondos.application.port.out.FundRepositoryPort;
import com.btg.fondos.domain.Fund;
import com.btg.fondos.infrastructure.persistence.FundDocument;
import com.btg.fondos.infrastructure.persistence.FundMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FundRepositoryAdapter implements FundRepositoryPort {

    private final FundMongoRepository fundMongoRepository;

    @Override
    public Optional<Fund> findById(String id) {
        return fundMongoRepository.findById(id).map(FundDocument::toDomain);
    }
}
