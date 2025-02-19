package org.main.criptoapi.fonds;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/fond")
public class FondControllerRest {

    private final FondsService fondsService;
    public FondControllerRest(FondsService fondsService) {
        this.fondsService = fondsService;
    }

    @GetMapping("/donnee/{id}")
    public ResponseEntity<?> getFond(@PathVariable Integer id){

        try {
            System.out.println("Miditra beeee");
            return ResponseEntity.status(HttpStatus.OK).body(fondsService.getFond(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/depot")
    public ResponseEntity<?> depot(@RequestBody FondDTO fondDTO){
        Map<String, Object> bodyContent = new HashMap<>();
        try {
            fondsService.depot(fondDTO);
            bodyContent.put("success", true);
            bodyContent.put("message", "Transaction successful");
            return ResponseEntity.status(HttpStatus.OK).body(bodyContent);
        }catch (IllegalArgumentException iae) {
            bodyContent.put("success", false);
            bodyContent.put("message", iae.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(bodyContent);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/retrait")
    public ResponseEntity<?> retrait(@RequestBody FondDTO fondDTO){

        Map<String, Object> bodyContent = new HashMap<>();
        try {
            fondsService.retrait(fondDTO);
            bodyContent.put("success", true);
            bodyContent.put("message", "Transaction successful");
            return ResponseEntity.status(HttpStatus.OK).body(bodyContent);
        }catch (IllegalArgumentException iae) {
            bodyContent.put("success", false);
            bodyContent.put("message", iae.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(bodyContent);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
