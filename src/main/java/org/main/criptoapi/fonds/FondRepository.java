package org.main.criptoapi.fonds;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FondRepository extends JpaRepository<Fond, Integer> {
    List<Fond> findByIduser(Integer iduser);
}