package com.btg.fondos.infrastructure.controller.dto;

import lombok.Data;

@Data
public class CancelRequest {
    private String clientId;
    private String fundId;
}
