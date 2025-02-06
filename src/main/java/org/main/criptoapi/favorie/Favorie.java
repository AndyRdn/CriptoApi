package org.main.criptoapi.favorie;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.main.criptoapi.crypto.Crypto;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "favorie")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Favorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "iduser")
    private Integer iduser;

    @Column(name = "daty")
    private LocalDateTime daty;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_crypto")
    private Crypto idCrypto;

}