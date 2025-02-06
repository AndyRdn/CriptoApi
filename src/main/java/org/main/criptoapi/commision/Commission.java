package org.main.criptoapi.commision;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.main.criptoapi.crypto.Crypto;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "commission")
public class Commission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_crypto")
    private Crypto idCrypto;

    @Column(name = "daty")
    private LocalDateTime daty;

    @Column(name = "montant")
    private Double montant;

}