package org.main.criptoapi.utilisateur;

import java.time.Instant;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UtilisateurDTO {
    private int id;
    private String nom;
    private String prenom;
    private Instant dateNaissance;
    private int genre;
    private String photoProfile;
    private String mail;

    public UtilisateurDTO() {}
}
