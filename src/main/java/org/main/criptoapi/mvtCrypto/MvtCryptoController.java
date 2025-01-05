package org.main.criptoapi.mvtCrypto;

import org.main.criptoapi.crypto.Crypto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mvt-crypto")
public class MvtCryptoController {
    private MvtCryptoService mvtCryptoService;

    public MvtCryptoController(MvtCryptoService mvtCryptoService) {
        this.mvtCryptoService = mvtCryptoService;
    }

    @GetMapping("/wallet/{idUser}")
    public ResponseEntity<?> getPortefeuille(@PathVariable Integer idUser) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(mvtCryptoService.getPortefeuille(idUser));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/lol")
    public String s() {
        Crypto c = mvtCryptoService.sellCrypto();
        return c.getNom();
    }
}
