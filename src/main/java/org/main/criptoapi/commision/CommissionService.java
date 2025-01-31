package org.main.criptoapi.commision;

import org.main.criptoapi.config.Config;
import org.main.criptoapi.config.ConfigService;
import org.main.criptoapi.crypto.Crypto;
import org.main.criptoapi.crypto.CryptoRepository;
import org.main.criptoapi.mvtCrypto.MvtCryptoService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CommissionService {
    private final CommissionRepository commissionRepository;
    private final ConfigService configService;
    private final CryptoRepository cryptoRepository;
    public CommissionService(CommissionRepository commissionRepository, ConfigService configService, CryptoRepository cryptoRepository) {
        this.commissionRepository = commissionRepository;
        this.configService = configService;
        this.cryptoRepository = cryptoRepository;
    }

    public void generateCommisionVente(Crypto c, double prix){
        double comsVente=Double.parseDouble(configService.getValueByKey("comsVente"));
        Commission commission=new Commission();
        commission.setDaty(LocalDate.now());
        commission.setIdCrypto(c);
        commission.setMontant((prix*comsVente)/100);
    }

    public void generateCommissionAchat(Crypto c, double prix){
        double comsAchat=Double.parseDouble(configService.getValueByKey("comsAchat"));
        Commission commission=new Commission();
        commission.setDaty(LocalDate.now());
        commission.setIdCrypto(c);
        commission.setMontant((prix*comsAchat)/100);
    }

    public List<CommissionResult> analyseComs(AnalyseComsDTO analyseComsDTO){
        if (analyseComsDTO.getTypeAnalyse()== null) analyseComsDTO.setTypeAnalyse("");
        if (analyseComsDTO.getTypeAnalyse().equals("avg")){
            return commissionRepository.findAvgMontantByCryptoIdsAndDateRange(analyseComsDTO.getCryptoIds(),analyseComsDTO.getMinDate(),analyseComsDTO.getMaxDate());
        }else if (analyseComsDTO.getTypeAnalyse().equals("sum")){
            return commissionRepository.findSumMontantByCryptoIdsAndDateRange(analyseComsDTO.getCryptoIds(),analyseComsDTO.getMinDate(),analyseComsDTO.getMaxDate());

        }else {
            System.out.println("ato ve alnsnlkalwkdnnwalkda");
            System.out.println("ato ve alnsnlkalwkdnnwalkda");
            System.out.println("ato ve alnsnlkalwkdnnwalkda");
            System.out.println("ato ve alnsnlkalwkdnnwalkda");
            analyseComsDTO.setCryptoIds(cryptoRepository.findAllCryptoIds());
            for (int i: analyseComsDTO.getCryptoIds()) System.out.println(i);
            return commissionRepository.findSumMontantByCryptoIdsAndDateRange(analyseComsDTO.getCryptoIds(),analyseComsDTO.getMinDate(),analyseComsDTO.getMaxDate());
        }
    }

    public void changeConfigV(int val){
        Config conf= configService.getByKey("comsVente");
        conf.setValue(String.valueOf(val));
    }
    public void changeConfigA(int val){
        Config conf= configService.getByKey("comsAchat");
        conf.setValue(String.valueOf(val));
    }

}
