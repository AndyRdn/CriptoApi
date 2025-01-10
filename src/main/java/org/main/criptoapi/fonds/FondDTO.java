package org.main.criptoapi.fonds;

import java.time.LocalDateTime;

public class FondDTO {
    private Integer id;
    private double somme;
    LocalDateTime daty;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getSomme() {
        return somme;
    }

    public void setSomme(double somme) {
        this.somme = somme;
    }

    public LocalDateTime getDaty() {
        return daty;
    }

    public void setDaty(LocalDateTime daty) {
        this.daty = daty;
    }
}
