package org.main.criptoapi.histoCrypto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/histoCrypto")
public class HistoCryptoController {

    @Autowired
    private HistoCryptoService histoCryptoService;

    @PostMapping("/generate")
    @ResponseBody
    public ResponseEntity<String> generateCryptoValues() {
        histoCryptoService.generateCryptoValues();
        return ResponseEntity.ok("Valeurs crypto générées avec succès");
    }
}