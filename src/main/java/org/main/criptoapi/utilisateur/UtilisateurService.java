package org.main.criptoapi.utilisateur;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UtilisateurService {
    private final ObjectMapper objectMapper;

    public UtilisateurService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public UtilisateurDTO fromJson(String json) throws Exception {
        return objectMapper.readValue(json, UtilisateurDTO.class);
    }
    
}
