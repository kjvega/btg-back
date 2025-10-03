package com.btg.fondos.application.service;

import com.btg.fondos.application.port.out.NotificationPort;
import com.btg.fondos.infrastructure.persistence.adapter.EmailNotificationAdapter;
import com.btg.fondos.infrastructure.persistence.adapter.SmsNotificationAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService implements NotificationPort {

    private final EmailNotificationAdapter emailAdapter;
    private final SmsNotificationAdapter smsAdapter;

    @Override
    public void sendNotification(String clientId, String message, String type) {
        if ("EMAIL".equalsIgnoreCase(type)) {
            emailAdapter.sendNotification(clientId, message, type);
        } else if ("SMS".equalsIgnoreCase(type)) {
            smsAdapter.sendNotification(clientId, message, type);
        } else {
            throw new IllegalArgumentException("Tipo de notificación no soportado: " + type);
        }
    }
}
