package org.main.criptoapi.fonds;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


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
    private LocalDateTime daty;

}