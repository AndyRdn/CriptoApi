package org.main.criptoapi.fonds;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fond")
public class FondController {

    private final FondsService fondsService;

    public FondController(FondsService fondsService) {
        this.fondsService = fondsService;
    }

    @GetMapping("/donnee")
    public double getFond(@RequestParam Integer id){
        return fondsService.getFond(id);
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
