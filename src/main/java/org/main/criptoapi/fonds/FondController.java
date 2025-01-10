package org.main.criptoapi.fonds;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fond")
public class FondController {

    private final FondsService fondsService;

    public FondController(FondsService fondsService) {
        this.fondsService = fondsService;
    }

    @GetMapping("/donnee")
    public ResponseEntity<?> getFond(@RequestParam Integer id){

        try {
            return ResponseEntity.status(HttpStatus.OK).body(fondsService.getFond(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/depot")
    public void depot(@ModelAttribute FondDTO fondDTO){
        fondsService.depot(fondDTO);
    }

    @PostMapping("/retrait")
    public void retrait(@ModelAttribute FondDTO fondDTO){
        fondsService.retrait(fondDTO);
    }

}
