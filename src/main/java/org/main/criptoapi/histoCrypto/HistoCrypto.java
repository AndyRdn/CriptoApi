package org.main.criptoapi.histoCrypto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.main.criptoapi.crypto.Crypto;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "histo_crypto")
public class HistoCrypto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_crypto")
    private Crypto idCrypto;

    @Column(name = "daty")
    private Instant daty;

    @Column(name = "valeur", precision = 15, scale = 2)
    private BigDecimal valeur;

}
