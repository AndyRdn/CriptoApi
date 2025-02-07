package org.main.criptoapi.favorie;

import org.main.criptoapi.crypto.Crypto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FavorieService {
    private final FavorieRepository favorieRepository;

    public FavorieService(FavorieRepository favorieRepository) {
        this.favorieRepository = favorieRepository;
    }

    public List<Favorie> getAll(){
        return favorieRepository.findAll();
    }

    @Transactional  // Assure que la session Hibernate reste ouverte
    public List<Favorie> getByIdUser(int id) {
        List<Favorie> favoris = favorieRepository.findByIduser(id);
        favoris.forEach(f -> f.getIdCrypto().getNom()); // Force le chargement de l'entité Crypto
        return favoris;
    }

    public Favorie addFavorie(int idUser, Crypto idCrypto){
        Favorie favorie=new Favorie();
        favorie.setDaty(LocalDateTime.now());
        favorie.setIduser(idUser);
        favorie.setIdCrypto(idCrypto);
        return favorieRepository.save(favorie);
    }

    public void removeFavorie(int id){
        Favorie favorie= favorieRepository.findById(id).get();
        favorieRepository.delete(favorie);
    }


}
