package org.main.criptoapi.mvtCrypto;

import java.util.List;
import org.main.criptoapi.crypto.Crypto;
import org.main.criptoapi.crypto.CryptoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.MediaType;
// import org.springframework.web.bind.annotation.Consumes;

@Controller
@RequestMapping("/mvt-crypto")
public class MvtCryptoController {
    private MvtCryptoService mvtCryptoService;
    private CryptoRepository cryptoRepository;

    public MvtCryptoController(MvtCryptoService mvtCryptoService, CryptoRepository cryptoRepository) {
        this.mvtCryptoService = mvtCryptoService;
        this.cryptoRepository = cryptoRepository;
    }

    @GetMapping("/wallet/{idUser}")
    public ModelAndView getWalletCrypto(@PathVariable Integer idUser) {
        ModelAndView mv = new ModelAndView("layout");
        mv.addObject("page", "mvt-crypto/portefeuille");

        List<SoldeCryptoDTO> solde = mvtCryptoService.getPortefeuille(idUser);
        System.out.println(idUser);
        mv.addObject("listCrypto", solde);

        return mv;
    }

    @PostMapping("/sell")
    // @Consumes(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String sellCrypto(@ModelAttribute CryptoTransactionDTO cryptoInfo) {
        try {
            Crypto c = cryptoRepository.findById(cryptoInfo.getIdCrypto()).get();

            mvtCryptoService.sellCrypto(cryptoInfo.getIdUser(), c, cryptoInfo.getQuantite());

            return "redirect:/mvt-crypto/wallet/" + cryptoInfo.getIdUser();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @PostMapping("/buy")
    public String buyCrypto(@RequestBody CryptoTransactionDTO cryptoInfo) {
        try {
            Crypto c = cryptoRepository.findById(cryptoInfo.getIdCrypto()).get();

            mvtCryptoService.buyCrypto(cryptoInfo.getIdUser(), c, cryptoInfo.getQuantite());

            return "Transaction réussie";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping("/list")
    public ModelAndView listMvt(){
        ModelAndView mv = new ModelAndView("layout");
        mv.addObject("page","mvt-crypto/liste-achats-ventes");
        try {
            mv.addObject("listeAchatVente", mvtCryptoService.getList());
        } catch (Exception e) {
            mv.addObject("error", e.getMessage());
        }

        return mv;
    }
    
}
