package com.btg.fondos.infrastructure.controller.dto;

import lombok.Data;

@Data
public class SubscribeRequest {
    private String clientId;
    private String fundId;
    private double amount;
    private String notificationType;
}
