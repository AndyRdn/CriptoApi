package org.main.criptoapi.fonds;

import org.main.criptoapi.auth.Layout;
import org.main.criptoapi.auth.LayoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("/fond")
public class FondController {

    private final LayoutService layoutService;
    private final FondsService fondsService;

    public FondController(LayoutService layoutService, FondsService fondsService) {
        this.layoutService = layoutService;
        this.fondsService = fondsService;
    }

//    @GetMapping("/donnee")
//    public ModelAndView getFond(@RequestParam Integer id) throws Exception {
//        try {
//            ModelAndView modelAndView=new ModelAndView("/profil/details");
//            modelAndView.addObject("fond",fondsService.getFond(id));
//            return modelAndView;
//        }catch (Exception e) {
//            throw new Exception(e.getMessage());
//        }
//    }

    @GetMapping("/donnee")
    public ModelAndView getFond() throws Exception {
        try {
            ModelAndView mv = new ModelAndView("layout");
            mv.addObject("page","profil/details");
//            modelAndView.addObject("fond",fondsService.getFond(id));
            return mv;
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
