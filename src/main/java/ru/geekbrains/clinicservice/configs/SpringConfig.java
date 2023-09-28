package ru.geekbrains.clinicservice.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.geekbrains.clinicservice.services.ClientRepository;
import ru.geekbrains.clinicservice.services.PetRepository;
import ru.geekbrains.clinicservice.services.impl.SqlLiteClientRepository;
import ru.geekbrains.clinicservice.services.impl.SqlLitePetRepository;

@Configuration
public class SpringConfig {
    @Bean(name = "clientRepository")
    public ClientRepository getClientRepository() {
        return new SqlLiteClientRepository();
    }

    @Bean(name = "petRepository")
    public PetRepository getPetRepository() {
        return new SqlLitePetRepository();
    }
}
