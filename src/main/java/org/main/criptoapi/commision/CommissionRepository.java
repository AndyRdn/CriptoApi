package org.main.criptoapi.commision;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CommissionRepository extends JpaRepository<Commission, Integer> {

    @Query("SELECT c.idCrypto.id, c.idCrypto.nom, SUM(c.montant) " +
            "FROM Commission c " +
            "WHERE c.idCrypto.id IN :cryptoIds " +
            "AND coalesce(:minDate, null ) is NULL or c.daty >= :minDate " +
            "AND coalesce(:maxDate, null ) is NULL or c.daty <= :maxDate " +
            "GROUP BY c.idCrypto.id")
    List<CommissionResult> findSumMontantByCryptoIdsAndDateRange(
            @Param("cryptoIds") List<Integer> cryptoIds,
            @Param("minDate") String minDate,
            @Param("maxDate") String maxDate
    );
    @Query("SELECT c.idCrypto.id, c.idCrypto.nom, SUM(c.montant) " +
            "FROM Commission c " +
            "WHERE c.idCrypto.id IN :cryptoIds " +
            "AND coalesce(:minDate, null ) is NULL or c.daty >= :minDate " +
            "AND coalesce(:maxDate, null ) is NULL or c.daty <= :maxDate " +
            "GROUP BY c.idCrypto.id")
    List<CommissionResult> findAvgMontantByCryptoIdsAndDateRange(
            @Param("cryptoIds") List<Integer> cryptoIds,
            @Param("minDate") String minDate,
            @Param("maxDate") String maxDate
    );
}