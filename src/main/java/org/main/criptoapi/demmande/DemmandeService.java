package org.main.criptoapi.demmande;

import org.main.criptoapi.fonds.Fond;
import org.main.criptoapi.fonds.FondDTO;
import org.main.criptoapi.fonds.FondRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemmandeService {

    @Autowired
    private DemmandeRepository demmandeRepository;

    @Autowired
    private FondRepository fondRepository;

    public void demmandeDepot(FondDTO fondDTO){
        Demmande demmande = new Demmande();
        demmande.setDepot(fondDTO.getSomme());
        demmande.setRetrait(0.0);
        demmande.setIduser(fondDTO.getId());
        demmande.setDaty(fondDTO.getDaty());
        demmande.setEtat(1);

        demmandeRepository.save(demmande);
    }

    public void demmandeRetrait(FondDTO fondDTO){
        Demmande demmande = new Demmande();
        demmande.setDepot(0.0);
        demmande.setRetrait(fondDTO.getSomme());
        demmande.setIduser(fondDTO.getId());
        demmande.setDaty(fondDTO.getDaty());
        demmande.setEtat(1);

        demmandeRepository.save(demmande);
    }

    public void validation(Integer id){
        Demmande demmande = demmandeRepository.findById(id).get();

        Fond fond = new Fond();
        fond.setIduser(demmande.getIduser());
        fond.setDepot(demmande.getDepot());
        fond.setRetrait(demmande.getRetrait());
        fond.setDaty(demmande.getDaty());

        demmande.setEtat(11);

        demmandeRepository.save(demmande);
        fondRepository.save(fond);
    }

    public void refus(Integer id){
        Demmande demmande = demmandeRepository.findById(id).get();

        demmande.setEtat(0);

        demmandeRepository.save(demmande);
    }

    public List<Demmande> demmandeToValidate(){
        return demmandeRepository.demmandeToValidate();
    }
}
