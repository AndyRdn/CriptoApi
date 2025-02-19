package org.main.criptoapi.mvtCrypto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.main.criptoapi.commision.CommissionService;
import org.main.criptoapi.config.ConfigService;
import org.main.criptoapi.crypto.Crypto;
import org.main.criptoapi.crypto.CryptoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/mvt-crypto")
public class MvtCryptoRestController {
    private MvtCryptoService mvtCryptoService;
    private CryptoRepository cryptoRepository;


    public MvtCryptoRestController(MvtCryptoService mvtCryptoService, CryptoRepository cryptoRepository) {
        this.mvtCryptoService = mvtCryptoService;
        this.cryptoRepository = cryptoRepository;

    }

    @GetMapping("/wallet/{idUser}")
    public ResponseEntity<?> getPortefeuille(@PathVariable Integer idUser) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(mvtCryptoService.getPortefeuille(idUser));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/sell")
    public ResponseEntity<?> sellCrypto(@RequestBody CryptoTransactionDTO cryptoInfo) {
        Map<String, Object> bodyContent = new HashMap<>();
        try {
            Crypto c = cryptoRepository.findById(cryptoInfo.getIdCrypto()).get();

            mvtCryptoService.sellCrypto(cryptoInfo.getIdUser(), c, cryptoInfo.getQuantite());
            bodyContent.put("success", true);
            bodyContent.put("message", "Transaction successful");

            return ResponseEntity.status(HttpStatus.OK).body(bodyContent);
        } catch (IllegalArgumentException iae) {
            bodyContent.put("success", false);
            bodyContent.put("message", iae.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(bodyContent);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/buy")
    public ResponseEntity<?> buyCrypto(@RequestBody CryptoTransactionDTO cryptoInfo) {
        Map<String, Object> bodyContent = new HashMap<>();
        try {
            Crypto c = cryptoRepository.findById(cryptoInfo.getIdCrypto()).get();

            mvtCryptoService.buyCrypto(cryptoInfo.getIdUser(), c, cryptoInfo.getQuantite());

            bodyContent.put("success", true);
            bodyContent.put("message", "Transaction successful");

            return ResponseEntity.status(HttpStatus.OK).body(bodyContent);
        } catch (IllegalArgumentException iae) {
            bodyContent.put("success", false);
            bodyContent.put("message", iae.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(bodyContent);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?> listMvt(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(mvtCryptoService.getList());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/list/{idUser}")
    public ResponseEntity<?> listMvtPerso(@PathVariable Integer idUser){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(mvtCryptoService.findByUser(idUser));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/search")
    public ResponseEntity<?> filtreAchatVente(@RequestBody FiltreAchatVenteDTO filtreAchatVenteDTO){
        try{
            String dateHeure = filtreAchatVenteDTO.getDateHeure();

            LocalDateTime dateTime = LocalDateTime.parse(dateHeure);

            ArrayList<FiltreAchatVente> filtreAchatVentes = mvtCryptoService.findFiltreAchatVenteByDate(dateTime);
            return ResponseEntity.status(HttpStatus.OK).body(filtreAchatVentes);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/historique/search")
    public ResponseEntity<?> searchHistorique(@RequestBody HistoriqueOperationDTO historiqueOperationDTO){
        try{
            ArrayList<MtvCrypto> searchHistorique = mvtCryptoService.searchHistorique(historiqueOperationDTO);
            return ResponseEntity.status(HttpStatus.OK).body(searchHistorique);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
