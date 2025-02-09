package org.main.criptoapi.utilisateur;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class NodeResponse<T> {
    private String status;
    private ApiData<T> data;
    private Object error;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ApiData<T> {
        private String message;
        private T data;
    }
}