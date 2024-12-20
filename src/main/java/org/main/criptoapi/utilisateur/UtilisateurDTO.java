package org.main.criptoapi.utilisateur;

import java.sql.Timestamp;

public class UtilisateurDTO {
    private int id;
    private String nom;
    private String prenom;
    private Timestamp dateNaissance;
    private int genre;
    private String mail;

    public UtilisateurDTO(int id, String nom, String prenom, Timestamp dateNaissance, int genre, String mail) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.genre = genre;
        this.mail = mail;
    }

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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Timestamp getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Timestamp dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public int getGenre() {
        return genre;
    }

    public void setGenre(int genre) {
        this.genre = genre;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
