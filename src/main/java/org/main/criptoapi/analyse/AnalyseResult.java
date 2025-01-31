package org.main.criptoapi.analyse;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AnalyseResult {
    private Integer id;
    private String nom;
    private double quartile;
    private double min;
    private double max;
    private double moyenne;
    private double ecartType;

    public AnalyseResult(Integer id, String nom, BigDecimal min, BigDecimal max, BigDecimal avg){
        this.id = id;
        this.nom = nom;
        this.min = min.doubleValue();
        this.max = max.doubleValue();
        this.moyenne = avg.doubleValue();
    }
}
