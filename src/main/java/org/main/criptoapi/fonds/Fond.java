package org.main.criptoapi.fonds;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "fonds")
public class Fond {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "iduser")
    private Integer iduser;

    @Column(name = "depot")
    private Double depot;

    @Column(name = "retrait")
    private Double retrait;

    @Column(name = "daty")
    private Instant daty;

}