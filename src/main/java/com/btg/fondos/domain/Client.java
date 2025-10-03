package com.btg.fondos.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    private String id;
    private String name;
    private double balance;
    private List<SubscribedFund> subscribedFunds = new ArrayList<>();
}
