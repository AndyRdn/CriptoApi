package org.main.criptoapi.config;

import org.springframework.stereotype.Service;

@Service
public class ConfigService {
    private final ConfigRepository configRepository;

    public ConfigService(ConfigRepository configRepository) {
        this.configRepository = configRepository;
    }

    public String getByKey(String key){
        return configRepository.findByKey(key).get().getKey();
    }
}
