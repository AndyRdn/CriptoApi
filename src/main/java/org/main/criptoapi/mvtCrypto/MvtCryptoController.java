package org.main.criptoapi.mvtCrypto;

import java.util.HashMap;
import java.util.Map;

import org.main.criptoapi.crypto.Crypto;
import org.main.criptoapi.crypto.CryptoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/mvt-crypto")
public class MvtCryptoController {
    private MvtCryptoService mvtCryptoService;
    private CryptoRepository cryptoRepository;

    public MvtCryptoController(MvtCryptoService mvtCryptoService, CryptoRepository cryptoRepository) {
        this.mvtCryptoService = mvtCryptoService;
        this.cryptoRepository = cryptoRepository;
    }

    @GetMapping("/wallet/{idUser}")
    public ResponseEntity<?> getPortefeuille(@PathVariable Integer idUser) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(mvtCryptoService.getPortefeuille(idUser));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/sell")
    public ResponseEntity<?> postMethodName(@RequestBody CryptoTransactionDTO cryptoInfo) {
        Map<String, Object> bodyContent = new HashMap<>();
        try {
            Crypto c = cryptoRepository.findById(cryptoInfo.getIdCrypto()).get();

            mvtCryptoService.sellCrypto(cryptoInfo.getIdUser(), c, cryptoInfo.getQuantite());

            bodyContent.put("success", true);
            bodyContent.put("message", "Transaction successful");

            return ResponseEntity.status(HttpStatus.OK).body(bodyContent);
        } catch (IllegalArgumentException iae) {
            bodyContent.put("success", false);
            bodyContent.put("message", iae.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(bodyContent);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
