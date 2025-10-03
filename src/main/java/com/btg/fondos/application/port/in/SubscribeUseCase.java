package com.btg.fondos.application.port.in;

import com.btg.fondos.domain.Transaction;

public interface SubscribeUseCase {
    Transaction subscribe(String clientId, String fundId, double amount, String notificationType);
}
