package org.main.criptoapi.utilisateur;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

@Service
public class UtilisateurService {
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;

    public UtilisateurService(ObjectMapper objectMapper, RestTemplate restTemplate) {
        this.objectMapper = objectMapper;
        this.restTemplate = restTemplate;
    }

    public UtilisateurDTO fromJson(String json) throws Exception {
        return objectMapper.readValue(json, UtilisateurDTO.class);
    }

    public UtilisateurDTO getUserById(int id) throws Exception {
        String url = "http://connectify:80/utilisateur/get-utilisateur/" + id;

        ResponseEntity<NodeResponse<UtilisateurDTO>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<NodeResponse<UtilisateurDTO>>() {
                }
        );
        return response.getBody().getData().getData();
    }

}
