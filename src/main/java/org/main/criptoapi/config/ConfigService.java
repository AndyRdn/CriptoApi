package org.main.criptoapi.config;

import org.springframework.stereotype.Service;

@Service
public class ConfigService {
    private final ConfigRepository configRepository;

    public ConfigService(ConfigRepository configRepository) {
        this.configRepository = configRepository;
    }

    public String getValueByKey(String key){
        return configRepository.findByKey(key).get().getValue();
    }
    public Config getByKey(String key){
        return configRepository.findByKey(key).get();
    }
}
