package org.main.criptoapi.commision;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CommissionRepository extends JpaRepository<Commission, Integer> {

    @Query("SELECT new org.main.criptoapi.commision.CommissionResult(c.idCrypto.id, c.idCrypto.nom, SUM(c.montant)) " +
            "FROM Commission c " +
            "WHERE (c.idCrypto.id IN :cryptoIds " +
            "AND (coalesce(:minDate, null) IS NULL OR c.daty >= :minDate) " +
            "AND (coalesce(:maxDate, null) IS NULL OR c.daty <= :maxDate)) " +
            "OR c.idCrypto.id IN :cryptoIds " +
            "GROUP BY c.idCrypto.id, c.idCrypto.nom")
    List<CommissionResult> findSumMontantByCryptoIdsAndDateRange(
            @Param("cryptoIds") List<Integer> cryptoIds,
            @Param("minDate") String minDate,
            @Param("maxDate") String maxDate
    );
    @Query("SELECT new org.main.criptoapi.commision.CommissionResult(c.idCrypto.id, c.idCrypto.nom, AVG(c.montant)) " +
            "FROM Commission c " +
            "WHERE (c.idCrypto.id IN :cryptoIds " +
            "AND (coalesce(:minDate, null) IS NULL OR c.daty >= :minDate) " +
            "AND (coalesce(:maxDate, null) IS NULL OR c.daty <= :maxDate)) " +
            "OR c.idCrypto.id IN :cryptoIds " +
            "GROUP BY c.idCrypto.id, c.idCrypto.nom")
    List<CommissionResult> findAvgMontantByCryptoIdsAndDateRange(
            @Param("cryptoIds") List<Integer> cryptoIds,
            @Param("minDate") String minDate,
            @Param("maxDate") String maxDate
    );
}