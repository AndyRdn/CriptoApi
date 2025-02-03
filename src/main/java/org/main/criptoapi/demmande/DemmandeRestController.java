package org.main.criptoapi.demmande;

import org.main.criptoapi.fonds.FondDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/demmande")
public class DemmandeRestController {
    @Autowired
    private DemmandeService demmandeService;

    @PostMapping("/retrait")
    public ResponseEntity<?> retrait(@RequestBody FondDTO fondDTO){
        try {

            demmandeService.demmandeRetrait(fondDTO);

            return ResponseEntity.status(HttpStatus.OK).body("{\"message\":\"Demmande de retrait envoyee\"}");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("{\"message\": \"" + e.getMessage()+"\"");
        }
    }

    @PostMapping("/depot")
    public ResponseEntity<?> depot(@RequestBody FondDTO fondDTO){
        try {

            demmandeService.demmandeDepot(fondDTO);

            return ResponseEntity.status(HttpStatus.OK).body("{\"message\":\"Demmande de depot envoyee\"}");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("{\"message\": \"" + e.getMessage()+"\"");
        }
    }

    @GetMapping("/validation/{idDemmande}")
    public ResponseEntity<?> validation(@PathVariable Integer idDemmande){
        try {

            demmandeService.validation(idDemmande);

            return ResponseEntity.status(HttpStatus.OK).body("{\"message\":\"Demmande Validee\"}");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("{\"message\": \"" + e.getMessage()+"\"");
        }
    }

    @GetMapping("/refus/{idDemmande}")
    public ResponseEntity<?> refus(@PathVariable Integer idDemmande){
        try {

            demmandeService.refus(idDemmande);

            return ResponseEntity.status(HttpStatus.OK).body("{\"message\":\"Demmande refusee\"}");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("{\"message\": \"" + e.getMessage()+"\"");
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> demmandeToValidate(){
        try{

            List<Demmande> demmandes = demmandeService.demmandeToValidate();
            return ResponseEntity.status(HttpStatus.OK).body(demmandes);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("{\"message\": \"" + e.getMessage()+"\"");
        }
    }
}
