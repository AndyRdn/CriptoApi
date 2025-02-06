package org.main.criptoapi.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.stereotype.Service;

@Service
public class RTDBService {

    private final FirebaseDatabase database;

    public RTDBService() {
        this.database = FirebaseDatabase.getInstance();
    }

    public void sendData(String path, Object data) {
        DatabaseReference ref = database.getReference(path);

        ref.push().setValueAsync(data)
                .addListener(() -> {
                    System.out.println("Okay, tafiditra le histo-crypto " + path);
                }, Runnable::run);
    }
}
