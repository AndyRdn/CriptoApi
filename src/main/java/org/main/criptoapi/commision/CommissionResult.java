package org.main.criptoapi.commision;

public class CommissionResult {
    int id;
    String nom;

    double montant;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public CommissionResult(int id, String nom, double montant) {
        this.id = id;
        this.nom = nom;
        this.montant = montant;
    }
}
