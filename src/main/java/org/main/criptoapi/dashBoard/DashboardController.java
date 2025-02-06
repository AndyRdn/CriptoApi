package org.main.criptoapi.dashBoard;
import org.main.criptoapi.histoCrypto.HistoCrypto;
import org.main.criptoapi.histoCrypto.HistoCryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private HistoCryptoService histoCryptoService;

    @GetMapping("/")
    public ModelAndView generateCryptoValues() {
        ModelAndView mv = new ModelAndView("dashboard/dashboard");
        List<HistoCrypto> histoCryptoList = histoCryptoService.findLastValueForEachCrypto(10);
        List<HistoCrypto> actuelles = histoCryptoService.findLastValueForEachCrypto();

        mv.addObject("histoCrypto", histoCryptoList);
        mv.addObject("actuelles", actuelles);

        return mv;
    }
}
