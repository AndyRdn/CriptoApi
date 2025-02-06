package org.main.criptoapi.mvtCrypto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.main.criptoapi.commision.CommissionService;
import org.main.criptoapi.crypto.Crypto;
import org.main.criptoapi.crypto.CryptoRepository;
import org.main.criptoapi.fonds.Fond;
import org.main.criptoapi.fonds.FondRepository;
import org.main.criptoapi.histoCrypto.HistoCrypto;
import org.main.criptoapi.histoCrypto.HistoCryptoService;
import org.main.criptoapi.notifications.NotificationService;
import org.main.criptoapi.fonds.FondsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.google.firebase.messaging.FirebaseMessagingException;

@Service
public class MvtCryptoService {
    JdbcTemplate jdbcTemplate;
    CryptoRepository cryptoRepository;
    MtvCryptoRepository mtvCryptoRepository;
    FondRepository fondRepository;
    HistoCryptoService histoCryptoService;
    FondsService fondService;
    CommissionService commissionService;
    NotificationService notificationService;

    public MvtCryptoService(JdbcTemplate jdbcTemplate, CryptoRepository cryptoRepository, MtvCryptoRepository mtvCryptoRepository, FondRepository fondRepository, HistoCryptoService histoCryptoService, FondsService fondsService, CommissionService commissionService, NotificationService notificationService) {
        this.jdbcTemplate = jdbcTemplate;
        this.notificationService = notificationService;
        this.cryptoRepository = cryptoRepository;
        this.mtvCryptoRepository = mtvCryptoRepository;
        this.fondRepository = fondRepository;
        this.fondService = fondsService;
        this.histoCryptoService = histoCryptoService;
        this.commissionService = commissionService;
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

                        double valeur = histoCryptoService.findActualValueCrypto(c.getId()).get().getValeur().doubleValue();
                        sc.setValeur(valeur);

                        double q = rs.getInt("quantite");
                        sc.setQuantite(q);

                        if(q >= 1) {
                            soldes.add(sc);
                        }

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

    public void sellCrypto(Integer idUser, Crypto c, int quantite) throws IllegalArgumentException, FirebaseMessagingException {
        Integer inAccount = getPortefeuille(idUser, c.getId());
        if (inAccount == null || inAccount.compareTo(quantite) < 0) {
            throw new IllegalArgumentException("Quantity of crypto to be sold must be inferior or equal to account balance");
        }

        HistoCrypto hc = histoCryptoService.findActualValueCrypto(c.getId()).get();
        BigDecimal decimalValue = hc.getValeur();
        Double cryptoValue = decimalValue.doubleValue(); 
        commissionService.generateCommisionVente(c,cryptoValue*quantite);
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

        notificationService.sendOperationNotification(newMvt);
    }

    public void buyCrypto(Integer idUser, Crypto c, int quantite) throws IllegalArgumentException, FirebaseMessagingException {
        Double inAccount = fondService.getFond(idUser); 

        HistoCrypto hc = histoCryptoService.findActualValueCrypto(c.getId()).get();
        BigDecimal decimalValue = hc.getValeur();
        Double cryptoValue = decimalValue.doubleValue(); 

        if (inAccount.compareTo(cryptoValue * quantite) < 0) {
            throw new IllegalArgumentException("Funds balance insufficient to buy " + quantite + " of " + c.getNom());
        }
        commissionService.generateCommissionAchat(c,cryptoValue*quantite);
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

        notificationService.sendOperationNotification(newMvt);

    }

    public List<MtvCrypto> getList(){
        return mtvCryptoRepository.findAll();
    }

    public ArrayList<FiltreAchatVente> findFiltreAchatVenteByDate(LocalDateTime dateTime){
        return mtvCryptoRepository.findFiltreAchatVenteByDate(dateTime);
    }

    ArrayList<MtvCrypto> findByUser(Integer idUser){
        return mtvCryptoRepository.findByUser(idUser);
    }

    public List<Map<String, Object>> getResultatsRequete(String requeteSql) {
        return jdbcTemplate.queryForList(requeteSql);
    }

    public ArrayList<MtvCrypto> searchHistorique(HistoriqueOperationDTO historiqueOperationDTO) {
        ArrayList<MtvCrypto> mtvCryptos = new ArrayList<>();

        if (!historiqueOperationDTO.getCrypto().equals("")) {
            mtvCryptos.addAll(mtvCryptoRepository.findByCrypto(Integer.parseInt(historiqueOperationDTO.getCrypto())));
        } else {
            mtvCryptos.addAll(mtvCryptoRepository.findAll());
        }

        if (!historiqueOperationDTO.getMinDate().equals("") && !historiqueOperationDTO.getMaxDate().equals("")) {
            mtvCryptos = filterByDate(mtvCryptos, historiqueOperationDTO.getMinDate(), historiqueOperationDTO.getMaxDate());
        } else if (!historiqueOperationDTO.getMinDate().equals("")) {
            mtvCryptos = filterByDateAfter(mtvCryptos, historiqueOperationDTO.getMinDate());
        } else if (!historiqueOperationDTO.getMaxDate().equals("")) {
            mtvCryptos = filterByDateBefore(mtvCryptos, historiqueOperationDTO.getMaxDate());
        }

        return mtvCryptos;
    }

    private ArrayList<MtvCrypto> filterByDate(ArrayList<MtvCrypto> mtvCryptos, String minDate, String maxDate) {
        return new ArrayList<>(mtvCryptos.stream()
                .filter(mtvCrypto -> mtvCrypto.getDaty().isAfter(LocalDateTime.parse(minDate)))
                .filter(mtvCrypto -> mtvCrypto.getDaty().isBefore(LocalDateTime.parse(maxDate)))
                .toList());
    }

    private ArrayList<MtvCrypto> filterByDateAfter(ArrayList<MtvCrypto> mtvCryptos, String minDate) {
        return new ArrayList<>(mtvCryptos.stream()
                .filter(mtvCrypto -> mtvCrypto.getDaty().isAfter(LocalDateTime.parse(minDate)))
                .toList());
    }

    private ArrayList<MtvCrypto> filterByDateBefore(ArrayList<MtvCrypto> mtvCryptos, String maxDate) {
        return new ArrayList<>(mtvCryptos.stream()
                .filter(mtvCrypto -> mtvCrypto.getDaty().isBefore(LocalDateTime.parse(maxDate)))
                .toList());
    }
}
