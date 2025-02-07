package org.main.criptoapi.firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.main.criptoapi.demmande.Demmande;
import org.main.criptoapi.demmande.DemmandeRepository;
import org.main.criptoapi.favorie.Favorie;
import org.main.criptoapi.favorie.FavorieRepository;
import org.main.criptoapi.mvtCrypto.MtvCrypto;
import org.main.criptoapi.mvtCrypto.MtvCryptoRepository;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutionException;

@Service
public class FirestoreService {

    private final Firestore firestore;
    private final FavorieRepository favorieRepository;
    private final MtvCryptoRepository mtvCryptoRepository;
    private final DemmandeRepository demandeRepository;

    public FirestoreService(Firestore firestore, FavorieRepository favorieRepository, MtvCryptoRepository mtvCryptoRepository, DemmandeRepository demandeRepository) {
        this.firestore = firestore;
        this.favorieRepository = favorieRepository;
        this.mtvCryptoRepository = mtvCryptoRepository;
        this.demandeRepository = demandeRepository;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void startListening(){
        System.out.println("MAMAKY NY FIREBASE REHETRA IZY");
        listenForFavoris();
        listenForMvtCrypto();
        listenForDemande();
        System.out.println("VOVAKY DAHOLO");
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
                                Favorie favoris = doc.toObject(Favorie.class);
                                if (favoris != null) {
                                    favorieRepository.save(favoris);
                                    System.out.println("🔥 Favoris Added: " + favoris);
                                }
                                break;
                            case REMOVED:
                                System.out.println("Favoris Deleted: " + docId);
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
                .orderBy("id", Query.Direction.DESCENDING)
                .limit(1)
                .addSnapshotListener((snapshots, error) -> {
                    if (error != null) {
                        System.err.println("Firestore Listener failed: " + error);
                        return;
                    }

                    Demmande demande = null;
                    for (DocumentSnapshot doc : snapshots.getDocuments()) {
                        demande = doc.toObject(Demmande.class);
                    }
                    demande.setId(null);
                    demandeRepository.save(demande);

                    System.out.println("Updated demande Data: " + demande);
                });
    }
}
