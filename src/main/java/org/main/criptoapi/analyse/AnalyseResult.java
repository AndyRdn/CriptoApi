package org.main.criptoapi.analyse;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AnalyseResult {
    private Integer id;
    private double quartile;
    private double min;
    private double max;
    private double ecartType;

    public AnalyseResult(Integer id, BigDecimal quartile, BigDecimal min, BigDecimal max, BigDecimal ecart){
        this.id = id;
        this.quartile = quartile.doubleValue();
        this.min = min.doubleValue();
        this.max = max.doubleValue();
        this.ecartType = ecart.doubleValue();
    }
}
