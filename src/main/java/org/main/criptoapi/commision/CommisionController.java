package org.main.criptoapi.commision;

import org.main.criptoapi.config.Config;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/commission")
public class CommisionController {
    private final CommissionService commissionService;

    public CommisionController(CommissionService commissionService) {
        this.commissionService = commissionService;
    }

    @PostMapping("/Analyse")
    public ResponseEntity<?> listAll(@RequestBody AnalyseComsDTO analyseComsDTO){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(commissionService.analyseComs(analyseComsDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/configVente")
    public ResponseEntity<?> configV(@RequestParam int valeur){
        try {
            commissionService.changeConfigV(valeur);
            return ResponseEntity.status(HttpStatus.OK).body("succes");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/configAchat")
    public ResponseEntity<?> configA(@RequestParam int valeur){
        try {
            commissionService.changeConfigA(valeur);
            return ResponseEntity.status(HttpStatus.OK).body("succes");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
