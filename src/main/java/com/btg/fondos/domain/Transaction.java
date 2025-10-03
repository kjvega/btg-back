package com.btg.fondos.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private String id;
    private String type;
    private String fund;
    private double amount;
    private Date date;
    private String clientId;
}
