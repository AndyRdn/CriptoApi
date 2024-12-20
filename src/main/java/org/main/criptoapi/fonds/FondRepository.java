package org.main.criptoapi.fonds;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FondRepository extends JpaRepository<Fond, Integer> {
}