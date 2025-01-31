package org.main.criptoapi.commision;

import java.util.List;

public class AnalyseComsDTO {
    private List<Integer> cryptoIds;
    private String minDate;
    private String maxDate;
    private String typeAnalyse;

    public String getTypeAnalyse() { return typeAnalyse; }
    public void setTypeAnalyse(String typeAnalyse) { this.typeAnalyse = typeAnalyse; }

    public String getMinDate() { return minDate; }
    public void setMinDate(String minDate) { this.minDate = minDate; }

    public String getMaxDate() { return maxDate; }
    public void setMaxDate(String maxDate) { this.maxDate = maxDate; }

    public List<Integer> getCryptoIds() { return cryptoIds; }
    public void setCryptoIds(List<Integer> cryptoIds) { this.cryptoIds = cryptoIds; }
}
