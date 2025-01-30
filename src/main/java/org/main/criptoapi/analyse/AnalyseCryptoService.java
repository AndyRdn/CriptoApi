package org.main.criptoapi.analyse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class AnalyseCryptoService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getResultatsRequete(String requeteSql) {
        return jdbcTemplate.queryForList(requeteSql);
    }

    public void traiterResultats(String minDate, String maxDate) {
        minDate = minDate.replace("T", " ");
        maxDate = maxDate.replace("T", " ");

        String condition = "";
        if(!minDate.equals("")){
            condition += " and daty >= '"+minDate+"'";
        }
        if(!maxDate.equals("")){
            condition += " and daty <= '"+maxDate+"'";
        }

        String requeteSql = "SELECT id_crypto, COALESCE(min(valeur), 0) as min, COALESCE(max(valeur), 0) as max, COALESCE(avg(valeur), 0) as avg FROM histo_crypto where 1=1"+condition+" group by id_crypto";
        System.out.println("Requete: "+requeteSql);

        List<Map<String, Object>> resultats = getResultatsRequete(requeteSql);

        for (Map<String, Object> row : resultats){
            int idCrypto = (Integer) row.get("id_crypto");
            BigDecimal min = (BigDecimal) row.get("min");
            BigDecimal max = (BigDecimal) row.get("max");
            BigDecimal avg = (BigDecimal) row.get("avg");


        }
    }
}
