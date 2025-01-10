package org.main.criptoapi.fonds;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/fond")
public class FondController {

    private final FondsService fondsService;

    public FondController(FondsService fondsService) {
        this.fondsService = fondsService;
    }

    @GetMapping("/donnee")
    public ModelAndView getFond(@RequestParam Integer id) throws Exception {
        try {
            ModelAndView modelAndView=new ModelAndView();
            modelAndView.addObject("fond",fondsService.getFond(id));
            return modelAndView;
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @PostMapping("/depot")
    public String depot(@ModelAttribute FondDTO fondDTO) throws Exception {
        try {
            fondsService.depot(fondDTO);
            return "redirect:/fond/donnee";
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @PostMapping("/retrait")
    public String retrait(@ModelAttribute FondDTO fondDTO) throws Exception {

        try {
            fondsService.retrait(fondDTO);
            return "redirect:/fond/donnee";
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
