package org.main.criptoapi.commision;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CommissionRepository extends JpaRepository<Commission, Integer> {

    @Query(value = "SELECT crypto.id AS idCrypto, crypto.nom AS nomCrypto, COALESCE(SUM(comm.montant), 0) AS avgMontant " +
            "FROM crypto " +
            "LEFT JOIN commission comm ON crypto.id = comm.id_crypto " +
            "AND (coalesce(:minDate, null) IS NULL OR comm.daty >= :minDate) " +
            "AND (coalesce(:minDate, null) IS NULL OR comm.daty <= :maxDate) " +
            "WHERE crypto.id IN (:cryptoIds) " +
            "GROUP BY crypto.id, crypto.nom", nativeQuery = true)
    List<CommissionResult> findSumMontantByCryptoIdsAndDateRange(
            @Param("cryptoIds") List<Integer> cryptoIds,
            @Param("minDate") LocalDateTime minDate,
            @Param("maxDate") LocalDateTime maxDate
    );

    @Query(value = "SELECT crypto.id AS idCrypto, crypto.nom AS nomCrypto, COALESCE(AVG(comm.montant), 0) AS avgMontant " +
            "FROM crypto " +
            "LEFT JOIN commission comm ON crypto.id = comm.id_crypto " +
            "AND (coalesce(:minDate, null) IS NULL OR comm.daty >= :minDate) " +
            "AND (coalesce(:minDate, null) IS NULL OR comm.daty <= :maxDate) " +
            "WHERE crypto.id IN (:cryptoIds) " +
            "GROUP BY crypto.id, crypto.nom", nativeQuery = true)
    List<CommissionResult> findAvgMontantByCryptoIdsAndDateRange(
            @Param("cryptoIds") List<Integer> cryptoIds,
            @Param("minDate") LocalDateTime minDate,
            @Param("maxDate") LocalDateTime maxDate
    );

}