package org.main.criptoapi.mvtCrypto;

import org.main.criptoapi.crypto.Crypto;
import org.main.criptoapi.crypto.CryptoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


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
    public ModelAndView getWalletCrypto(Integer idUser) {
        ModelAndView mv = new ModelAndView("");
        mv.addObject("listCrypto", mvtCryptoService.getPortefeuille(idUser));

        return mv;
    }

    @PostMapping("/sell")
    public String sellCrypto(@RequestBody CryptoTransactionDTO cryptoInfo) {
        try {
            Crypto c = cryptoRepository.findById(cryptoInfo.getIdCrypto()).get();

            mvtCryptoService.sellCrypto(cryptoInfo.getIdUser(), c, cryptoInfo.getQuantite());

            return "Transaction réussie";
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
        ModelAndView mv = new ModelAndView("");
        try {
            mv.addObject("listeAchatVente", mvtCryptoService.getList());
        } catch (Exception e) {
            mv.addObject("error", e.getMessage());
        }

        return mv;
    }
    
}
