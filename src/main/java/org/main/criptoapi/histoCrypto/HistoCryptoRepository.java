package org.main.criptoapi.histoCrypto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HistoCryptoRepository extends JpaRepository<HistoCrypto, Integer> {

    @Query("select hc from HistoCrypto hc where hc.idCrypto.id= :idCrypto order by hc.id desc LIMIT 1")
    Optional<HistoCrypto> findActualCryptoValue(@Param("idCrypto") Integer idCrypto);

    @Query(value = "select hc from HistoCrypto hc order by hc.id desc Limit :limit", nativeQuery = false)
    List<HistoCrypto> findLastValueForEachCrypto(@Param("limit") Integer size);

}
