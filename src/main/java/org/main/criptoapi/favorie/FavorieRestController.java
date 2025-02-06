package org.main.criptoapi.favorie;

import org.main.criptoapi.crypto.Crypto;
import org.main.criptoapi.crypto.CryptoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/favorie")
public class FavorieRestController {

    private final FavorieService favorieService;
    private final CryptoRepository cryptoRepository;

    public FavorieRestController(FavorieService favorieService, CryptoRepository cryptoRepository) {
        this.favorieService = favorieService;
        this.cryptoRepository = cryptoRepository;
    }

    @GetMapping("/list")
    public ResponseEntity<?> getfavorie(@RequestParam("userId") int userId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(favorieService.getByIdUser(userId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/addFavorie")
    public ResponseEntity<?> configV(@RequestBody Map<String, String> payload) {
        try {
            int user = Integer.parseInt(payload.get("idUser"));
            Crypto crypto = cryptoRepository.findById(Integer.parseInt(payload.get("idCrypto"))).get();
            favorieService.addFavorie(user,crypto);
            return ResponseEntity.status(HttpStatus.OK).body("succes");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/remove/{id}")
    public ResponseEntity<?> deleteFav(@RequestBody int id) {
        try {
            favorieService.removeFavorie(id);
            return ResponseEntity.status(HttpStatus.OK).body("succes");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
