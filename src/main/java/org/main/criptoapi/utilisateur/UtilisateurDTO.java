package org.main.criptoapi.utilisateur;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UtilisateurDTO {
    private int id;
    private String nom;
    private String prenom;
    private LocalDateTime dateNaissance;
    private int genre;
    private String mail;

    public UtilisateurDTO(int id, String nom, String prenom, LocalDateTime dateNaissance, int genre, String mail) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.genre = genre;
        this.mail = mail;
    }
}
