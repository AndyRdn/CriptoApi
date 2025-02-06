package org.main.criptoapi.demmande;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "demmande")
public class Demmande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "id_user")
    private Integer iduser;

    @Column(name = "depot")
    private Double depot;

    @Column(name = "retrait")
    private Double retrait;

    @Column(name = "daty")
    private LocalDateTime daty;

    @Column(name = "etat")
    private Integer etat;

}
