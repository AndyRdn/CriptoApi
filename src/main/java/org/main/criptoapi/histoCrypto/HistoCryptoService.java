package org.main.criptoapi.histoCrypto;

import org.main.criptoapi.crypto.Crypto;
import org.main.criptoapi.crypto.CryptoRepository;
import org.main.criptoapi.firebase.FirestoreService;
import org.main.criptoapi.firebase.RTDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class HistoCryptoService {

    @Autowired
    private HistoCryptoRepository histoCryptoRepository;

    @Autowired
    private CryptoRepository cryptoRepository;

    @Autowired
    private FirestoreService rtdbService;

    public Optional<HistoCrypto> findActualValueCrypto(Integer idCrypto){
        return histoCryptoRepository.findActualCryptoValue(idCrypto);
    }

    public List<HistoCrypto> findLastValueForEachCrypto(){
        List<Crypto> cryptoList = cryptoRepository.findAll();
        return histoCryptoRepository.findLastValueForEachCrypto(cryptoList.size());
    }

    public List<HistoCrypto> findLastValueForEachCrypto(int nb){
        return histoCryptoRepository.findLastValueForEachCrypto(nb);
    }

   @Scheduled(fixedRate = 10000)
    public void generateCryptoValues(){
        List<Crypto> cryptoList = cryptoRepository.findAll();
        List<HistoCrypto> histoCryptoList = histoCryptoRepository.findLastValueForEachCrypto(cryptoList.size());
        for (int i=0; i<cryptoList.size(); i++){
            HistoCrypto histoCrypto = new HistoCrypto();
            histoCrypto.setIdCrypto(histoCryptoList.get(i).getIdCrypto());

            Instant instant = histoCryptoList.get(i).getDaty();
            histoCrypto.setDaty(instant.plusSeconds(10));

            BigDecimal valeur = this.randomValue(histoCryptoList.get(i).getValeur());
            histoCrypto.setValeur(valeur);

            histoCryptoRepository.save(histoCrypto);
            HistoCryptoFirebase histoCryptoFirebase = new HistoCryptoFirebase(histoCrypto.getId(), histoCrypto.getIdCrypto(), histoCrypto.getDaty());
            histoCryptoFirebase.convertBigDecimalToDouble(histoCrypto.getValeur());
            rtdbService.sendData("histo-crypto", histoCryptoFirebase.getId().toString(), histoCryptoFirebase);
        }

        System.out.println("Crypto mis a jour");
    }

    public BigDecimal randomValue(BigDecimal bigDecimal) {
        BigDecimal currentValue = bigDecimal;
        double val = currentValue.doubleValue();
        double minusDelta = -val * 0.3;
        double minus2Delta = -val * 0.6;
        double plusDelta = val * 0.3;
        double plus2Delta = val * 0.6;
        double[] deltas = {minusDelta, minus2Delta, plusDelta, plus2Delta};
        Random random = new Random();
        int randomIndex = random.nextInt(4);
        System.out.println("randomPercentage: " + randomIndex);
        BigDecimal delta = BigDecimal.valueOf(deltas[randomIndex]);
        BigDecimal newValue = currentValue.add(delta);
        newValue = newValue.setScale(2, RoundingMode.HALF_UP);
        return newValue;
    }

    public List<HistoCrypto> findAll(){
        return histoCryptoRepository.findAll();
    }
    
    public List<HistoCrypto> findLastValue(Integer id) {
        return histoCryptoRepository.findLastValue(id);
    }
}
