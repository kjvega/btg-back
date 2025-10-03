package com.btg.fondos.application.port.out;

public interface NotificationPort {
    void sendNotification(String clientId, String message, String type);
}
