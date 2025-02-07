package org.main.criptoapi.firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.main.criptoapi.crypto.Crypto;
import org.main.criptoapi.crypto.CryptoRepository;
import org.main.criptoapi.demmande.Demmande;
import org.main.criptoapi.demmande.DemmandeRepository;
import org.main.criptoapi.favorie.Favorie;
import org.main.criptoapi.favorie.FavorieRepository;
import org.main.criptoapi.mvtCrypto.MtvCrypto;
import org.main.criptoapi.mvtCrypto.MtvCryptoRepository;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.ExecutionException;

@Service
public class FirestoreService {

    private final Firestore firestore;
    private final FavorieRepository favorieRepository;
    private final MtvCryptoRepository mtvCryptoRepository;
    private final DemmandeRepository demandeRepository;
    private final CryptoRepository cryptoRepository;

    public FirestoreService(Firestore firestore, FavorieRepository favorieRepository, MtvCryptoRepository mtvCryptoRepository, DemmandeRepository demandeRepository, CryptoRepository cryptoRepository) {
        this.firestore = firestore;
        this.favorieRepository = favorieRepository;
        this.mtvCryptoRepository = mtvCryptoRepository;
        this.demandeRepository = demandeRepository;
        this.cryptoRepository = cryptoRepository;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void startListening(){
        listenForFavoris();
        listenForDemande();
    }

    public void sendData(String collection, String id, Object data) {
        CollectionReference ref = firestore.collection(collection);
        DocumentReference docRef = ref.document(id);

        ApiFuture<WriteResult> result = docRef.set(data);

        try {
            System.out.println("Okay, tafiditra le "+collection+" " + result.get().getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void updateData(String collection, String id, Object data) {
        DocumentReference ref = firestore.collection(collection).document(id);
        ApiFuture<WriteResult> result = ref.set(data);
        try {
            System.out.println("Okay, niova le "+collection+" " + id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listenForFavoris() {
        firestore.collection("favoris")
                .addSnapshotListener((snapshots, error) -> {
                    if (error != null) {
                        System.err.println("Firestore Listener failed: " + error.getMessage());
                        return;
                    }

                    if (snapshots == null || snapshots.isEmpty()) {
                        System.out.println("No changes detected in favoris.");
                        return;
                    }

                    for (DocumentChange change : snapshots.getDocumentChanges()) {
                        DocumentSnapshot doc = change.getDocument();
                        String docId = doc.getId();

                        switch (change.getType()) {
                            case ADDED:
                                Favorie favoris = new Favorie();
                                favoris.setId(null);

                                if (doc.contains("iduser")) {
                                    Object idUserObj = doc.get("iduser");
                                    if (idUserObj instanceof Number) {
                                        favoris.setIduser(((Number) idUserObj).intValue());
                                    }
                                }

                                com.google.cloud.Timestamp ts = doc.getTimestamp("daty");
                                if (ts != null) {
                                    LocalDateTime ldt = ts.toDate().toInstant()
                                            .atZone(ZoneId.systemDefault())
                                            .toLocalDateTime();
                                    favoris.setDaty(ldt);
                                }

                                if (doc.contains("idCrypto")) {
                                    Object etatObj = doc.get("idCrypto");
                                    if (etatObj instanceof Number) {
                                        Crypto temp = cryptoRepository.findById(((Number) etatObj).intValue()).get();
                                        favoris.setIdCrypto(temp);
                                    }
                                }

                                favorieRepository.save(favoris);
                                System.out.println("🔥 Favoris Added: " + favoris);
                                break;
                            case REMOVED:
                                System.out.println("🔥 Favoris Deleted: " + docId);
                                favorieRepository.deleteById(Integer.parseInt(docId));
                                break;
                        }
                    }
                });
    }

    public void listenForMvtCrypto() {
        firestore.collection("mvt_crypto")
                .orderBy("id", Query.Direction.DESCENDING)
                .limit(1)
                .addSnapshotListener((snapshots, error) -> {
                    if (error != null) {
                        System.err.println("Firestore Listener failed: " + error);
                        return;
                    }

                    MtvCrypto mvtCrypto = null;
                    for (DocumentSnapshot doc : snapshots.getDocuments()) {
                        mvtCrypto = doc.toObject(MtvCrypto.class);
                    }
                    mvtCrypto.setId(null);
                    mtvCryptoRepository.save(mvtCrypto);

                    System.out.println("Updated mvtCrypto Data: " + mvtCrypto);
                });
    }

    public void listenForDemande() {
        firestore.collection("demande")
                .addSnapshotListener((snapshots, error) -> {
                    if (error != null) {
                        System.err.println("Firestore Listener failed: " + error);
                        return;
                    }

                    if (snapshots == null || snapshots.isEmpty()) {
                        System.out.println("No changes detected in demande.");
                        return;
                    }

                    for (DocumentChange change : snapshots.getDocumentChanges()) {
                        try {
                            DocumentSnapshot doc = change.getDocument();

                            switch (change.getType()) {
                                case ADDED:
                                    Demmande demande = new Demmande();

                                    demande.setId(null);

                                    if (doc.contains("iduser")) {
                                        Object idUserObj = doc.get("iduser");
                                        if (idUserObj instanceof Number) {
                                            demande.setIduser(((Number) idUserObj).intValue());
                                        }
                                    }

                                    if (doc.contains("depot")) {
                                        Object depotObj = doc.get("depot");
                                        if (depotObj instanceof Number) {
                                            demande.setDepot(((Number) depotObj).doubleValue());
                                        }
                                    }

                                    if (doc.contains("retrait")) {
                                        Object retraitObj = doc.get("retrait");
                                        if (retraitObj instanceof Number) {
                                            demande.setRetrait(((Number) retraitObj).doubleValue());
                                        }
                                    }

                                    com.google.cloud.Timestamp ts = doc.getTimestamp("daty");
                                    if (ts != null) {
                                        LocalDateTime ldt = ts.toDate().toInstant()
                                                .atZone(ZoneId.systemDefault())
                                                .toLocalDateTime();
                                        demande.setDaty(ldt);
                                    }

                                    if (doc.contains("etat")) {
                                        Object etatObj = doc.get("etat");
                                        if (etatObj instanceof Number) {
                                            demande.setEtat(((Number) etatObj).intValue());
                                        }
                                    }

                                    demandeRepository.save(demande);
                                    System.out.println("🔥 Demande Added: " + demande);
                                    break;
                            }
                        } catch (Exception e) {
                            System.err.println("Error processing change: " + e.getMessage());
                        }
                    }
                });
    }
}
