package org.main.criptoapi.listCrypto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.main.criptoapi.crypto.Crypto;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "listcrypto")
public class Listcrypto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_crypto")
    private Crypto idCrypto;

    @Column(name = "daty")
    private LocalDateTime daty;

}