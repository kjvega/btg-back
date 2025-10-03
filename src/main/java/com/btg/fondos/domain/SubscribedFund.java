package com.btg.fondos.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscribedFund {
    private String fundId;
    private String name;
    private double investedAmount;
    private Date date;
}
