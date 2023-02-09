package it.unicam.cs.ids.loyaltyplatform.configuration;

import it.unicam.cs.ids.loyaltyplatform.dao.AziendaRepository;
import it.unicam.cs.ids.loyaltyplatform.model.Azienda;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AziendaConfig {
    @Bean
    CommandLineRunner aziendaCommandLineRunner(AziendaRepository repository) {
        return args -> {
            Azienda sony = new Azienda(
                    "Sony",
                    "Via sony 1",
                    "partitaIva1");
            Azienda openAi = new Azienda(
                    "openAi",
                    "Via openAi 1",
                    "partitaIva2");
            repository.deleteAll();
            repository.saveAll(List.of(sony, openAi));
        };
    }
}
