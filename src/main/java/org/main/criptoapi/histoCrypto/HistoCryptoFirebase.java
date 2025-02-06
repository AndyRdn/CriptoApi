package org.main.criptoapi.histoCrypto;

import lombok.Data;
import org.main.criptoapi.crypto.Crypto;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class HistoCryptoFirebase {

    private Integer id;

    private Crypto idCrypto;

    private Instant daty;

    private Double valeur;

    public HistoCryptoFirebase(Integer id, Crypto idCrypto, Instant daty) {
        this.id = id;
        this.idCrypto = idCrypto;
        this.daty = daty;
    }

    public void convertBigDecimalToDouble(BigDecimal bigDecimal) {
        setValeur(bigDecimal.doubleValue());
    }

}
