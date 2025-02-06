package org.main.criptoapi.listCrypto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListcryptoRepository extends JpaRepository<Listcrypto, Integer> {
}