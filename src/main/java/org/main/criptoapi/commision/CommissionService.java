package org.main.criptoapi.commision;

import org.main.criptoapi.config.Config;
import org.main.criptoapi.config.ConfigRepository;
import org.main.criptoapi.config.ConfigService;
import org.main.criptoapi.crypto.Crypto;
import org.main.criptoapi.crypto.CryptoRepository;
import org.main.criptoapi.mvtCrypto.MvtCryptoService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommissionService {
    private final CommissionRepository commissionRepository;
    private final ConfigService configService;
    private final CryptoRepository cryptoRepository;
    private final ConfigRepository configRepository;

    public CommissionService(CommissionRepository commissionRepository, ConfigService configService, CryptoRepository cryptoRepository,
                             ConfigRepository configRepository) {
        this.commissionRepository = commissionRepository;
        this.configService = configService;
        this.cryptoRepository = cryptoRepository;
        this.configRepository = configRepository;
    }

    public void generateCommisionVente(Crypto c, double prix){
        double comsVente=Double.parseDouble(configService.getValueByKey("comsVente"));
        Commission commission=new Commission();
        commission.setDaty(LocalDateTime    .now());
        commission.setIdCrypto(c);
        commission.setMontant((prix*comsVente)/100);
    }

    public void generateCommissionAchat(Crypto c, double prix){
        double comsAchat=Double.parseDouble(configService.getValueByKey("comsAchat"));
        Commission commission=new Commission();
        commission.setDaty(LocalDateTime.now());
        commission.setIdCrypto(c);
        commission.setMontant((prix*comsAchat)/100);
    }

    public List<CommissionResult> analyseComs(AnalyseComsDTO analyseComsDTO){
        System.out.println(analyseComsDTO.getTypeAnalyse());
        LocalDateTime minDate = (analyseComsDTO.getMinDate() != null) ? LocalDateTime.parse(analyseComsDTO.getMinDate()) : null;
        LocalDateTime maxDate = (analyseComsDTO.getMaxDate() != null) ? LocalDateTime.parse(analyseComsDTO.getMaxDate()) : null;
        if (analyseComsDTO.getTypeAnalyse().equals("avg")){
            for (int i: analyseComsDTO.getCryptoIds()) System.out.println(i);
            return commissionRepository.findAvgMontantByCryptoIdsAndDateRange(analyseComsDTO.getCryptoIds(),minDate,maxDate);
        }else if (analyseComsDTO.getTypeAnalyse().equals("sum")){
            return commissionRepository.findSumMontantByCryptoIdsAndDateRange(analyseComsDTO.getCryptoIds(),minDate,maxDate);
        }else {
            analyseComsDTO.setCryptoIds(cryptoRepository.findAllCryptoIds());
            for (int i: analyseComsDTO.getCryptoIds()) System.out.println(i);
            return commissionRepository.findSumMontantByCryptoIdsAndDateRange(analyseComsDTO.getCryptoIds(),minDate,maxDate);
        }
    }

    public void changeConfigV(int val){
        Config conf= configService.getByKey("comsVente");
        conf.setValue(String.valueOf(val));
        configRepository.save(conf);
    }
    public void changeConfigA(int val){
        Config conf= configService.getByKey("comsAchat");
        conf.setValue(String.valueOf(val));
        configRepository.save(conf);
    }

}
