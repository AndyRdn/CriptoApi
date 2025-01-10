package org.main.criptoapi.fonds;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class FondsService {
    private final FondRepository fondRepository;
    public FondsService(FondRepository fondRepository) {
        this.fondRepository = fondRepository;
    }
    public double getFond(@RequestParam Integer id){
        double somme=0;
        System.out.println(id);
        List<Fond> fonds= fondRepository.findByIduser(id);
        for (Fond f: fonds){
            System.out.println(f.getDepot());
            System.out.println(f.getRetrait());
            somme+= f.getDepot();
            somme-= f.getRetrait();
        }

        return somme;
    }

    public void depot(FondDTO fondDTO){
        Fond fond = new Fond();
        fond.setIduser(fondDTO.getId());
        fond.setDaty(fondDTO.getDaty());
        fond.setRetrait(0.0);
        fond.setDepot(fondDTO.getSomme());
        fondRepository.save(fond);
    }

    public void retrait(FondDTO fondDTO){
        Fond fond = new Fond();
        fond.setIduser(fondDTO.getId());
        fond.setDaty(fondDTO.getDaty());
        fond.setRetrait(fondDTO.getSomme());
        fond.setDepot(0.0);
        fondRepository.save(fond);
    }
}
