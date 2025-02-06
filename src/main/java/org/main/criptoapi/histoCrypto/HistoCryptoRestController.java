package org.main.criptoapi.histoCrypto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/histoCrypto")
@CrossOrigin(origins = "http://localhost:5173")
public class HistoCryptoRestController {

    @Autowired
    private HistoCryptoService histoCryptoService;

    @PostMapping("/generate")
    public ResponseEntity<String> generateCryptoValues() {
        histoCryptoService.generateCryptoValues();
        return ResponseEntity.ok("Valeurs crypto générées avec succès");
    }

    @GetMapping("/current-value/{id}")
    public ResponseEntity<?> getMethodName(@PathVariable Integer id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(histoCryptoService.findLastValue(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    
}
