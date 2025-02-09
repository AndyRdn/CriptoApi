package org.main.criptoapi.notifications;

import com.google.firebase.messaging.*;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.main.criptoapi.demmande.Demmande;
import org.main.criptoapi.mvtCrypto.MtvCrypto;
import org.main.criptoapi.utilisateur.UtilisateurDTO;
import org.main.criptoapi.utilisateur.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final UtilisateurService utilisateurService;

    public NotificationService(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

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

    private String forgeCryptoMessage(MtvCrypto mvt) {
        String typeTransact = mvt.getAchat() == 0 ? "vendu" : "acheté";
        Integer qte = mvt.getAchat() == 0 ? mvt.getVente() : mvt.getAchat();

        UtilisateurDTO user;
        try {
            user = utilisateurService.getUserById(mvt.getIdUser());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String body = String.format("%s a %s %d de %s à %.2f € l'unité", user.getNom(), typeTransact, qte, mvt.getIdCrypto().getNom(), mvt.getValeur());
        
        return body;
    }

    private String forgeDemandeMessage(Demmande d) {
        String typeDemande = d.getRetrait() < 0.01 ? "dépôt" : "retrait";

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("d MMMM yyyy 'à' HH'h'mm", Locale.FRANCE);
        String formattedDateTime1 = d.getDaty().format(formatter1);

        double valeur = d.getRetrait() < 0.01 ? d.getDepot() : d.getRetrait();

        String etat = d.getEtat() == 0 ? "refusée" : "acceptée";

        String body = String.format("Votre demande de %s du %s, de %.2f €, a été %s", typeDemande, formattedDateTime1, valeur, etat);
        return body;
    }

    public void sendOperationNotification(MtvCrypto mvt) throws FirebaseMessagingException {
        String topic = "crypto-" + mvt.getIdCrypto().getId();
        String title = mvt.getIdCrypto().getNom();
        String body = forgeCryptoMessage(mvt);

        this.sendToTopic(topic, title, body);
    }

    public void sendDemandeNotification(Demmande d) throws FirebaseMessagingException {
        String topic = "demandes-user-" + d.getIduser();

        String typeDemande = d.getRetrait() < 0.01 ? "Dépôt" : "Retrait";
        String etat = d.getEtat() == 0 ? "refusé" : "accepté";
        String title = typeDemande + " " + etat;

        String body = forgeDemandeMessage(d);

        this.sendToTopic(topic, title, body);
    }
}