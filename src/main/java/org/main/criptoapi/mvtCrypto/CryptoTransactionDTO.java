package org.main.criptoapi.mvtCrypto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CryptoTransactionDTO {
    public CryptoTransactionDTO() {
    }

    Integer idUser;
    Integer idCrypto;
    Integer quantite;
}
