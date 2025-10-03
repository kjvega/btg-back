package com.btg.fondos.application.port.in;

import com.btg.fondos.domain.Transaction;

public interface CancelUseCase {
    Transaction cancel(String clientId, String fundId);
}

