package org.main.criptoapi.mvtCrypto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class FiltreAchatVente {
    private Integer idUser;
    private Double totalAchat;
    private Double totalVente;
    private Double portefeuille;

    public FiltreAchatVente(Integer idUser, BigDecimal totalAchat, BigDecimal totalVente, BigDecimal portefeuille) {
        this.idUser = idUser;
        this.totalAchat = totalAchat.doubleValue();
        this.totalVente = totalVente.doubleValue();
        this.portefeuille = portefeuille.doubleValue();
    }
}