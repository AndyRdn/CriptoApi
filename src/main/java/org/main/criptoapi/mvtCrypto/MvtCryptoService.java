package org.main.criptoapi.mvtCrypto;

import java.util.ArrayList;
import java.util.List;

import org.main.criptoapi.crypto.Crypto;
import org.main.criptoapi.crypto.CryptoRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class MvtCryptoService {
    JdbcTemplate jdbcTemplate;
    CryptoRepository cryptoRepository;

    public MvtCryptoService(JdbcTemplate jdbcTemplate, CryptoRepository cryptoRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.cryptoRepository = cryptoRepository;
    }

    public List<SoldeCryptoDTO> getPortefeuille(Integer idUser) {
        String sql = "SELECT id_crypto, quantite FROM v_solde_cryptos WHERE id_user = " + idUser;
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
                new Object[] {});

    }

    public Crypto sellCrypto() {
        return cryptoRepository.findById(1).get();
    }
}
