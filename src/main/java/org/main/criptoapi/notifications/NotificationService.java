package org.main.criptoapi.notifications;

import com.google.firebase.messaging.*;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    
    public String sendToTopic(String topic, String title, String content) 
        throws FirebaseMessagingException {
        
        Message message = Message.builder()
                .setNotification(Notification.builder()
                        .setTitle(title)
                        .setBody(content)
                        .build())
                .setTopic(topic)
                .build();

        return FirebaseMessaging.getInstance().send(message);
    }
}