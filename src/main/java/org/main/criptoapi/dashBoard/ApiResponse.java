package org.main.criptoapi.dashBoard;

import java.util.List;
import org.main.criptoapi.histoCrypto.HistoCrypto;

public class ApiResponse {
    private String status;
    private Data data;
    private String error;

    public ApiResponse(String status, Data data, String error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public static class Data {
        private List<HistoCrypto> historique;
        private List<HistoCrypto> actuelles;
        public Data(List<HistoCrypto> historique, List<HistoCrypto> actuelles) {
            this.historique = historique;
            this.actuelles = actuelles;
        }

        public List<HistoCrypto> getHistorique() {
            return historique;
        }

        public void setHistorique(List<HistoCrypto> historique) {
            this.historique = historique;
        }

        public List<HistoCrypto> getActuelles() {
            return actuelles;
        }

        public void setActuelles(List<HistoCrypto> actuelles) {
            this.actuelles = actuelles;
        }
    }
}