package com.btg.fondos.application.service;

import com.btg.fondos.application.port.in.ClientUseCase;
import com.btg.fondos.application.port.out.ClientRepositoryPort;
import com.btg.fondos.domain.Client;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService implements ClientUseCase {

    private final ClientRepositoryPort clientRepository;

    public ClientService(ClientRepositoryPort clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
}
