package com.btg.fondos.application.port.out;

import com.btg.fondos.domain.Fund;

import java.util.Optional;

public interface FundRepositoryPort {
    Optional<Fund> findById(String id);
}
