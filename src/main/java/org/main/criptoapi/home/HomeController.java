package org.main.criptoapi.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping("/")
    public ModelAndView redirectToLogin(){
        ModelAndView mav = new ModelAndView("login/login");
        return mav;
    }

}
