package org.main.criptoapi.mvtCrypto;

import org.main.criptoapi.crypto.Crypto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SoldeCryptoDTO {
    @JsonIgnore
    Integer idUser;

    Crypto crypto;
    
    Double quantite;

    public SoldeCryptoDTO() {
    }

    public SoldeCryptoDTO(Integer idUser, Crypto crypto, Double quantite) {
        this.idUser = idUser;
        this.crypto = crypto;
        this.quantite = quantite;
    }

}
