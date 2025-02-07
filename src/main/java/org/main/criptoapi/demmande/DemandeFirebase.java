package org.main.criptoapi.demmande;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@Setter
public class DemandeFirebase {

    private Integer id;

    private Integer iduser;

    private Double depot;

    private Double retrait;

    private Instant daty;

    private Integer etat;

    public DemandeFirebase() {
    }

    public DemandeFirebase(Integer id, Integer iduser, Double depot, Double retrait, LocalDateTime daty, Integer etat) {
        this.id = id;
        this.iduser = iduser;
        this.depot = depot;
        this.retrait = retrait;
        setDaty(daty);
        this.etat = etat;
    }

    public void setDaty(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.of("Indian/Antananarivo");
        if (localDateTime == null || zoneId == null) {
            return;
        }
        this.daty = localDateTime.atZone(zoneId).toInstant();
    }
}
