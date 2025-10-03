package com.btg.fondos.application.port.out;

import com.btg.fondos.domain.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepositoryPort {
    Optional<Client> findById(String id);
    Client save(Client client);
    List<Client> findAll();
}
