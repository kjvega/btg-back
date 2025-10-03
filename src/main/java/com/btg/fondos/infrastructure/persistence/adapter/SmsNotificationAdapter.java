package com.btg.fondos.infrastructure.persistence.adapter;

import com.btg.fondos.application.port.out.NotificationPort;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SmsNotificationAdapter implements NotificationPort {

    @Value("${twilio.accountSid}")
    private String accountSid;

    @Value("${twilio.authToken}")
    private String authToken;

    @Value("${twilio.fromNumber}")
    private String fromNumber;

    @Override
    public void sendNotification(String clientId, String message, String type) {
        // inicializa Twilio
        Twilio.init(accountSid, authToken);

        // Número del cliente (ejemplo estático, debería venir de la BD)
        String toNumber = "+573114439302";

        Message.creator(
                new com.twilio.type.PhoneNumber(toNumber),
                new com.twilio.type.PhoneNumber(fromNumber),
                message
        ).create();

        System.out.println("📱 SMS enviado a " + toNumber);
    }
}
