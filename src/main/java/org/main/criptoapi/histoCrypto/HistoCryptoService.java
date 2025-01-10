package org.main.criptoapi.histoCrypto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class HistoCryptoService {

    @Autowired
    private HistoCryptoRepository histoCryptoRepository;

    public Optional<HistoCrypto> findActualValueCrypto(Integer idCrypto){
        return histoCryptoRepository.findActualCryptoValue(idCrypto);
    }

    public void generateCryptoValues(){
        List<HistoCrypto> histoCryptoList = histoCryptoRepository.findLastValueForEachCrypto();
        for (int i=0; i<histoCryptoList.size(); i++){
            HistoCrypto histoCrypto = new HistoCrypto();
            histoCrypto.setIdCrypto(histoCryptoList.get(i).getIdCrypto());

            Instant instant = histoCryptoList.get(i).getDaty();
            histoCrypto.setDaty(instant.plusSeconds(10));

            BigDecimal valeur = this.randomValue(histoCryptoList.get(i).getValeur());
            histoCrypto.setValeur(valeur);

            histoCryptoRepository.save(histoCrypto);
        }
    }

    public BigDecimal randomValue(BigDecimal bigDecimal){
        BigDecimal currentValue = bigDecimal;
        Random random = new Random();
        double randomPercentage = random.nextDouble() * 0.6 - 0.3;
        BigDecimal percentageChange = currentValue.multiply(BigDecimal.valueOf(randomPercentage));
        BigDecimal newValue = currentValue.add(percentageChange);
        newValue = newValue.setScale(2, RoundingMode.HALF_UP);

        return newValue;
    }
}
