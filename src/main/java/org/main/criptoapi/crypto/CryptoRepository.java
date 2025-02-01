package org.main.criptoapi.crypto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CryptoRepository extends JpaRepository<Crypto, Integer> {
    @Query("SELECT c.id FROM Crypto c")
    List<Integer> findAllCryptoIds();
}