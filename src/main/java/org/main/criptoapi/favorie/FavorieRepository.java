package org.main.criptoapi.favorie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavorieRepository extends JpaRepository<Favorie, Integer> {
    List<Favorie> findByIduser(Integer iduser);
}