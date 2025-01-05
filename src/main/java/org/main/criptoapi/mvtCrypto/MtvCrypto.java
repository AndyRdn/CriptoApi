package org.main.criptoapi.mvtCrypto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.main.criptoapi.crypto.Crypto;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "mtv_crypto")
public class MtvCrypto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_crypto")
    private Crypto idCrypto;

    @Column(name = "id_user")
    private Integer idUser;

    @Column(name = "achat")
    private Integer achat;

    @Column(name = "vente")
    private Integer vente;

    @Column(name = "daty")
    private LocalDateTime daty;

    @Column(name = "valeur", precision = 15, scale = 2)
    private Double valeur;

}