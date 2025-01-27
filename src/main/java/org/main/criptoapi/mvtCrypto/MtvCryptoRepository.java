package org.main.criptoapi.mvtCrypto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MtvCryptoRepository extends JpaRepository<MtvCrypto, Integer> {
}