package org.main.criptoapi.mvtCrypto;

import lombok.Getter;
import lombok.Setter;
import org.main.criptoapi.crypto.Crypto;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@Setter
public class MvtCryptoFirebase {

    private Integer id;

    private Crypto idCrypto;

    private Integer idUser;

    private Integer achat;

    private Integer vente;

    private Instant daty;

    private Double valeur;

    public MvtCryptoFirebase() {
    }

    public MvtCryptoFirebase(Integer id, Crypto idCrypto, Integer idUser, Integer achat, Integer vente, LocalDateTime daty, BigDecimal valeur) {
        this.id = id;
        this.idCrypto = idCrypto;
        this.idUser = idUser;
        this.achat = achat;
        this.vente = vente;
        setDaty(daty);
        setValeur(valeur);
    }

    public void setDaty(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.of("Indian/Antananarivo");
        if (localDateTime == null || zoneId == null) {
            return;
        }
        this.daty = localDateTime.atZone(zoneId).toInstant();
    }

    public void setValeur(BigDecimal bigDecimal) {
        this.valeur = bigDecimal.doubleValue();
    }

}
