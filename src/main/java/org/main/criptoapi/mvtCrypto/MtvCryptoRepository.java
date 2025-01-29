package org.main.criptoapi.mvtCrypto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Repository
public interface MtvCryptoRepository extends JpaRepository<MtvCrypto, Integer> {
    @Query("SELECT new org.main.criptoapi.mvtCrypto.FiltreAchatVente(" +
            "m.idUser, " +
            "SUM(m.achat * m.valeur), " +
            "SUM(m.vente * m.valeur), " +
            "SUM(m.achat * m.valeur) - SUM(m.vente * m.valeur)) " +
            "FROM MtvCrypto m " +
            "WHERE m.daty < :date " +
            "GROUP BY m.idUser")
    ArrayList<FiltreAchatVente> findFiltreAchatVenteByDate(@Param("date") LocalDateTime date);
}