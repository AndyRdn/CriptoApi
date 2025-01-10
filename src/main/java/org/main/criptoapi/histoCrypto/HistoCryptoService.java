package org.main.criptoapi.histoCrypto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HistoCryptoService {

    @Autowired
    private HistoCryptoRepository histoCryptoRepository;

    public Optional<HistoCrypto> findActualValueCrypto(Integer idCrypto){
        return histoCryptoRepository.findActualCryptoValue(idCrypto);
    }

}
