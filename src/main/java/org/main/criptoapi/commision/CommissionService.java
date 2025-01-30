package org.main.criptoapi.commision;

import org.main.criptoapi.config.ConfigService;
import org.main.criptoapi.crypto.Crypto;
import org.main.criptoapi.mvtCrypto.MvtCryptoService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CommissionService {
    private final CommissionRepository commissionRepository;
    private final ConfigService configService;
    public CommissionService(CommissionRepository commissionRepository, ConfigService configService) {
        this.commissionRepository = commissionRepository;
        this.configService = configService;
    }

    public void generateCommisionVente(Crypto c, double prix){
        double comsVente=Double.parseDouble(configService.getByKey("comsVente"));
        Commission commission=new Commission();
        commission.setDaty(LocalDate.now());
        commission.setIdCrypto(c);
        commission.setMontant((prix*comsVente)/100);
    }

    public void generateCommissionAchat(Crypto c, double prix){
        double comsAchat=Double.parseDouble(configService.getByKey("comsAchat"));
        Commission commission=new Commission();
        commission.setDaty(LocalDate.now());
        commission.setIdCrypto(c);
        commission.setMontant((prix*comsAchat)/100);
    }

}
