package org.main.criptoapi.notifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.messaging.FirebaseMessagingException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/notifications")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/send")
    public String getMethodName() {
        try {
            notificationService.sendToTopic("nouveau_topic", "Bonjouuuuur", "Spring boot source");
            return "ok";
        } catch (FirebaseMessagingException e) {
            return e.getMessage();
        }
    }

    @GetMapping("/send-crypto/{id}")
    public String sendCryptos(@PathVariable int id) {
        try {
            notificationService.sendToTopic("crypto-" + id, "Crypto " + id, "Un utilisateur a acheté crypto " + id);
            return "ok";
        } catch (FirebaseMessagingException e) {
            return e.getMessage();
        }
    }
}
