package com.btg.fondos.infrastructure.persistence.adapter;

import com.btg.fondos.application.port.out.ClientRepositoryPort;
import com.btg.fondos.domain.Client;
import com.btg.fondos.infrastructure.persistence.ClientDocument;
import com.btg.fondos.infrastructure.persistence.ClientMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ClientRepositoryAdapter implements ClientRepositoryPort {

    private final ClientMongoRepository clientMongoRepository;

    @Override
    public List<Client> findAll() {
        return clientMongoRepository.findAll()
                .stream()
                .map(ClientDocument::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Client> findById(String id) {
        return clientMongoRepository.findById(id).map(ClientDocument::toDomain);
    }

    @Override
    public Client save(Client client) {
        return clientMongoRepository.save(ClientDocument.fromDomain(client)).toDomain();
    }
}
