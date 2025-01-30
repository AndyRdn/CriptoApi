package org.main.criptoapi.analyse;

import org.main.criptoapi.crypto.Crypto;
import org.main.criptoapi.crypto.CryptoRepository;
import org.main.criptoapi.histoCrypto.HistoCryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/analyse/crypto")
public class AnalyseCryptoRestController {

    @Autowired
    private CryptoRepository cryptoRepository;

    @Autowired
    private  AnalyseCryptoService analyseCryptoService;

    @GetMapping("/list")
    public ResponseEntity<List<Crypto>> getListeCrypto() {
        List<Crypto> cryptos = cryptoRepository.findAll();

        if (cryptos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(cryptos);
    }

    @PostMapping("/resultat")
    public ResponseEntity<?> analyseCrypto(@RequestBody AnalyseRequest analyseRequest) {
        try {
            String typeAnalyse = analyseRequest.getTypeAnalyse();
            String minDate = analyseRequest.getMinDate();
            String maxDate = analyseRequest.getMaxDate();
            List<Integer> cryptoIds = analyseRequest.getCryptoIds();

            if (cryptoIds == null || cryptoIds.isEmpty() || typeAnalyse == null || minDate == null || maxDate == null) {
                return ResponseEntity.badRequest().body("Missing or invalid parameters");
            }

            System.out.println("typeAnalyse: " + typeAnalyse);
            System.out.println("minDate: " + minDate);
            System.out.println("maxDate: " + maxDate);
            System.out.println("cryptoIds: " + cryptoIds);

            analyseCryptoService.traiterResultats(minDate, maxDate);

            return ResponseEntity.status(HttpStatus.OK).body("{ \"resultat\":\"ok\" }");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error during analysis: " + e.getMessage());
        }
    }
}
