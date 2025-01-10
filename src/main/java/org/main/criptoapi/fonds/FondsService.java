package org.main.criptoapi.fonds;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class FondsService {
    private final FondRepository fondRepository;
    public FondsService(FondRepository fondRepository) {
        this.fondRepository = fondRepository;
    }
    public double getFond(@RequestParam Integer id){
        double somme=0;
        List<Fond> fonds= fondRepository.findByIduser(id);
        for (Fond f: fonds){
            somme+= f.getDepot();
            somme-= f.getRetrait();
        }

        return somme;
    }

    public void depot(double somme, Integer user, LocalDateTime daty){
        Fond fond = new Fond();
        fond.setIduser(user);
        fond.setDaty(daty);
        fond.setRetrait(0.0);
        fond.setDepot(somme);
        fondRepository.save(fond);
    }

    public void retrait(double somme, Integer user, LocalDateTime daty){
        Fond fond = new Fond();
        fond.setIduser(user);
        fond.setDaty(daty);
        fond.setRetrait(somme);
        fond.setDepot(0.0);
        fondRepository.save(fond);
    }
}
