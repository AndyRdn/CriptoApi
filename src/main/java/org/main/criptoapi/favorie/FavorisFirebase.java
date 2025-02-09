package org.main.criptoapi.favorie;

import lombok.Getter;
import lombok.Setter;
import org.main.criptoapi.crypto.Crypto;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@Setter
public class FavorisFirebase {
    private Integer id;
    private Integer idUser;
    private Instant daty;
    private Crypto idCrypto;

    public FavorisFirebase() {
    }

    public FavorisFirebase(Integer id, Integer idUser, LocalDateTime daty, Crypto idCrypto) {
        this.id = id;
        this.idUser = idUser;
        setDaty(daty);
        this.idCrypto = idCrypto;
    }

    public void setDaty(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.of("Indian/Antananarivo");
        if (localDateTime == null || zoneId == null) {
            return;
        }
         this.daty = localDateTime.atZone(zoneId).toInstant();
    }
}
