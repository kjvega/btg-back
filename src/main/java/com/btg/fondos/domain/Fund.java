package com.btg.fondos.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fund {
    private String id;
    private String name;
    private double minimumAmount;
    private String category;
}
