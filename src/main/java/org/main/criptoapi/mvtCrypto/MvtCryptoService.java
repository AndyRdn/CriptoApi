package org.main.criptoapi.mvtCrypto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.main.criptoapi.crypto.Crypto;
import org.main.criptoapi.crypto.CryptoRepository;
import org.main.criptoapi.fonds.Fond;
import org.main.criptoapi.fonds.FondRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class MvtCryptoService {
    JdbcTemplate jdbcTemplate;
    CryptoRepository cryptoRepository;
    MtvCryptoRepository mtvCryptoRepository;
    FondRepository fondRepository;

    public MvtCryptoService(JdbcTemplate jdbcTemplate, CryptoRepository cryptoRepository, MtvCryptoRepository mtvCryptoRepository, FondRepository fondRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.cryptoRepository = cryptoRepository;
        this.mtvCryptoRepository = mtvCryptoRepository;
        this.fondRepository = fondRepository;
    }

    public List<SoldeCryptoDTO> getPortefeuille(Integer idUser) {
        String sql = "SELECT id_crypto, quantite FROM v_solde_cryptos WHERE id_user = ?";
        return jdbcTemplate.query(
                sql,
                rs -> {
                    List<SoldeCryptoDTO> soldes = new ArrayList<>();

                    while (rs.next()) {
                        SoldeCryptoDTO sc = new SoldeCryptoDTO();
                        sc.setIdUser(idUser);

                        int cryptoId = rs.getInt("id_crypto");
                        Crypto c = cryptoRepository.findById(cryptoId).get();
                        sc.setCrypto(c);

                        double q = rs.getInt("quantite");
                        sc.setQuantite(q);

                        soldes.add(sc);
                    }

                    return soldes;
                },
                new Object[] { idUser });
    }

    public Integer getPortefeuille(Integer idUser, Integer idCrypto) {
        String sql = "SELECT quantite FROM v_solde_cryptos WHERE id_user = ? AND id_crypto = ?";

        return jdbcTemplate.query(
                sql,
                rs -> {

                    if (rs.next()) {
                        return Integer.valueOf(rs.getInt("quantite"));
                    }

                    return null;

                },
                new Object[] { idUser, idCrypto });
    }

    public void sellCrypto(Integer idUser, Crypto c, int quantite) throws IllegalArgumentException {
        Integer inAccount = getPortefeuille(idUser, c.getId());
        if (inAccount == null || inAccount.compareTo(quantite) < 0) {
            throw new IllegalArgumentException("Quantity of crypto to be sold must be inferior or equal to account balance");
        }

        Double cryptoValue = 1D; // placeholder value, waiting for methods from Tsinjo

        MtvCrypto newMvt = new MtvCrypto();
        newMvt.setDaty(LocalDateTime.now());
        newMvt.setIdCrypto(c);
        newMvt.setIdUser(idUser);
        newMvt.setAchat(0);
        newMvt.setVente(quantite);
        newMvt.setValeur(BigDecimal.valueOf(cryptoValue));

        newMvt = mtvCryptoRepository.save(newMvt);

        Fond f = new Fond();
        f.setIduser(idUser);
        f.setDepot(cryptoValue * quantite);
        f.setRetrait(0D);
        f.setDaty(LocalDateTime.now());

        f = fondRepository.save(f);
    }

    public void buyCrypto(Integer idUser, Crypto c, int quantite) throws IllegalArgumentException {
        Double inAccount = 100000000D; // placeholder value, waiting for method from Andy
        Double cryptoValue = 1D; // placeholder value, waiting for methods from Tsinjo
        if (inAccount.compareTo(cryptoValue * quantite) < 0) {
            throw new IllegalArgumentException("Funds balance insufficient to buy " + quantite + " of " + c.getNom());
        }

        MtvCrypto newMvt = new MtvCrypto();
        newMvt.setDaty(LocalDateTime.now());
        newMvt.setIdCrypto(c);
        newMvt.setIdUser(idUser);
        newMvt.setAchat(quantite);
        newMvt.setVente(0);
        newMvt.setValeur(BigDecimal.valueOf(cryptoValue));

        newMvt = mtvCryptoRepository.save(newMvt);

        Fond f = new Fond();
        f.setIduser(idUser);
        f.setDepot(0D);
        f.setRetrait(cryptoValue * quantite);
        f.setDaty(LocalDateTime.now());

        f = fondRepository.save(f);
    }
}
