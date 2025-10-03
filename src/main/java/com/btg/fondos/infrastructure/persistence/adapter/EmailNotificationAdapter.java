package com.btg.fondos.infrastructure.persistence.adapter;

import com.btg.fondos.application.port.out.NotificationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailNotificationAdapter implements NotificationPort {

    private final JavaMailSender mailSender;

    @Override
    public void sendNotification(String clientId, String message, String type) {
        // Aquí deberías obtener el correo real del cliente desde la base
        String to = "destinatario@example.com";

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(to);
        mail.setSubject("Notificación - " + type);
        mail.setText(message);

        mailSender.send(mail);

        System.out.println("📧 Email enviado a " + to + " con asunto: " + mail.getSubject());
    }
}
