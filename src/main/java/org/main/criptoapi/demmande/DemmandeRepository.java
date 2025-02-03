package org.main.criptoapi.demmande;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemmandeRepository extends JpaRepository<Demmande, Integer> {

    @Query("SELECT d from Demmande d where d.etat = 1")
    public List<Demmande> demmandeToValidate();
}
