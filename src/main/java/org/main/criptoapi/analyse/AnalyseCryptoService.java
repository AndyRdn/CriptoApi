package org.main.criptoapi.analyse;

import org.main.criptoapi.crypto.Crypto;
import org.main.criptoapi.crypto.CryptoRepository;
import org.main.criptoapi.histoCrypto.HistoCrypto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AnalyseCryptoService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CryptoRepository cryptoRepository;

    public List<Map<String, Object>> getResultatsRequete(String requeteSql) {
        return jdbcTemplate.queryForList(requeteSql);
    }

    public List<AnalyseResponse> traiterResultats(AnalyseRequest analyseRequest) throws Exception {
        String minDate = analyseRequest.getMinDate().replace("T", " ");
        String maxDate = analyseRequest.getMaxDate().replace("T", " ");

        String condition = "";
        if(!minDate.equals("")){
            condition += " and hc.daty >= '"+minDate+"'";
        }
        if(!maxDate.equals("")){
            condition += " and hc.daty <= '"+maxDate+"'";
        }

        String requeteSql = "SELECT c.id, c.nom, COALESCE(min(hc.valeur), 0) AS min, COALESCE(max(hc.valeur), 0) AS max, COALESCE(avg(hc.valeur), 0) AS avg FROM Crypto c LEFT JOIN histo_crypto hc ON c.id = hc.id_crypto"+ condition +" GROUP BY c.id, c.nom";
        System.out.println("Requete: "+requeteSql);

        List<Map<String, Object>> resultats = getResultatsRequete(requeteSql);

        List<AnalyseResult> analyseResults = new ArrayList<>();
        for (Map<String, Object> row : resultats){
            int idCrypto = (Integer) row.get("id");
            BigDecimal min = (BigDecimal) row.get("min");
            BigDecimal max = (BigDecimal) row.get("max");
            BigDecimal avg = (BigDecimal) row.get("avg");
            String nom = (String) row.get("nom");

            if(analyseRequest.getCryptoIds().contains(idCrypto)){

                AnalyseResult analyseResult = new AnalyseResult(idCrypto, nom, min, max, avg);
                generateEcartTypeQuartile(analyseResult, analyseRequest);

                analyseResults.add(analyseResult);
            }
        }

        List<AnalyseResponse> analyseResponses = response(analyseResults, analyseRequest.getTypeAnalyse());
        return analyseResponses;
    }

    public List<AnalyseResponse> response(List<AnalyseResult> analyseResults, String typeString)throws Exception{
        int type = Integer.parseInt(typeString);
        List<AnalyseResponse> analyseResponses = new ArrayList<>();
        double valeur = 0;
        for (int i = 0; i < analyseResults.size(); i++) {
            AnalyseResponse analyseResponse = new AnalyseResponse();
            analyseResponse.setNom(analyseResults.get(i).getNom());

            if(type == 1){
                valeur = analyseResults.get(i).getQuartile();
            } else if (type == 2) {
                valeur = analyseResults.get(i).getMax();
            } else if (type == 3) {
                valeur = analyseResults.get(i).getMin();
            } else if (type == 4) {
                valeur = analyseResults.get(i).getMoyenne();
            } else {
                valeur = analyseResults.get(i).getEcartType();
            }
            analyseResponse.setValeur(valeur);
            analyseResponses.add(analyseResponse);
        }
        return analyseResponses;
    }

    public void generateEcartTypeQuartile(AnalyseResult analyseResult, AnalyseRequest analyseRequest){
        String minDate = analyseRequest.getMinDate().replace("T", " ");
        String maxDate = analyseRequest.getMaxDate().replace("T", " ");

        String condition = "";
        if(!minDate.equals("")){
            condition += " and daty >= '"+minDate+"'";
        }
        if(!maxDate.equals("")){
            condition += " and daty <= '"+maxDate+"'";
        }

        String requeteSql = "SELECT valeur FROM histo_crypto where id_crypto = "+analyseResult.getId()+""+condition+" order by valeur";
        List<Double> valeurCrypto = new ArrayList<>();

        List<Map<String, Object>> resultats = getResultatsRequete(requeteSql);
        for (Map<String, Object> row : resultats){
            BigDecimal valeur = (BigDecimal) row.get("valeur");

            valeurCrypto.add(valeur.doubleValue());
        }

        analyseResult.setEcartType(calculEcartType(valeurCrypto, analyseResult.getMoyenne()));
        analyseResult.setQuartile(calculQuartile(valeurCrypto));
    }

    public double calculEcartType (List<Double> valeurCrypto, double avg){

        if(valeurCrypto.size()==0) return 0;

        double ecartType = 0;
        for (int i = 0; i < valeurCrypto.size(); i++) {
            double valeur = valeurCrypto.get(i);
            ecartType += Math.pow(valeur-avg, 2);
        }

        ecartType /= valeurCrypto.size()-1;
        ecartType = Math.sqrt(ecartType);

        return ecartType;
    }

    public double calculQuartile(List<Double> valeurCrypto){

        if(valeurCrypto.size()==0) return 0;

        double position = (valeurCrypto.size()+1)/4;
        if(position%1==0){
            return valeurCrypto.get((int)position-1);
        }else {
            int up = (int) Math.floor(position);
            int down = (int) Math.ceil(position);

            double valeurUp = valeurCrypto.get(up-1);
            double valeurDown = valeurCrypto.get(down-1);

            return (valeurUp+valeurDown)/2;
        }
    }
}
