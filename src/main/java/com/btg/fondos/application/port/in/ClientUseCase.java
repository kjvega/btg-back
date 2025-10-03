package com.btg.fondos.application.port.in;

import com.btg.fondos.domain.Client;
import java.util.List;

public interface ClientUseCase {
    List<Client> getAllClients();
}
