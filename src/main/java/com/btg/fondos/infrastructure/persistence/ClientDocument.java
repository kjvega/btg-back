package com.btg.fondos.infrastructure.persistence;

import com.btg.fondos.domain.Client;
import com.btg.fondos.domain.SubscribedFund;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "clients")
public class ClientDocument {
    @Id
    private String id;
    private String name;
    private double balance;
    private List<SubscribedFund> subscribedFunds = new ArrayList<>();

    // Domain -> Document
    public static ClientDocument fromDomain(Client client) {
        ClientDocument doc = new ClientDocument();
        doc.setId(client.getId());
        doc.setName(client.getName());
        doc.setBalance(client.getBalance());
        doc.setSubscribedFunds(new ArrayList<>(client.getSubscribedFunds()));
        return doc;
    }

    // Document -> Domain
    public Client toDomain() {
        Client client = new Client();
        client.setId(id);
        client.setName(name);
        client.setBalance(balance);
        client.setSubscribedFunds(new ArrayList<>(subscribedFunds));
        return client;
    }
}
