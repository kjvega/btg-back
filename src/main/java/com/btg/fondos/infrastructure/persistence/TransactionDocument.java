package com.btg.fondos.infrastructure.persistence;

import com.btg.fondos.domain.Transaction;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Document(collection = "transactions")
public class TransactionDocument {
    @Id
    private String id;
    private String type;
    private String fund;
    private double amount;
    private Date date;
    private String clientId;

    public static TransactionDocument fromDomain(Transaction tx) {
        TransactionDocument doc = new TransactionDocument();
        doc.setId(tx.getId());
        doc.setType(tx.getType());
        doc.setFund(tx.getFund());
        doc.setAmount(tx.getAmount());
        doc.setDate(tx.getDate());
        doc.setClientId(tx.getClientId());
        return doc;
    }

    public Transaction toDomain() {
        Transaction tx = new Transaction();
        tx.setId(id);
        tx.setType(type);
        tx.setFund(fund);
        tx.setAmount(amount);
        tx.setDate(date);
        tx.setClientId(clientId);
        return tx;
    }
}
