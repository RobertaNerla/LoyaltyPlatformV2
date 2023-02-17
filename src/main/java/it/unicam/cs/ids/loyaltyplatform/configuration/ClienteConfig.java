package it.unicam.cs.ids.loyaltyplatform.configuration;


import it.unicam.cs.ids.loyaltyplatform.dao.ClienteRepository;
import it.unicam.cs.ids.loyaltyplatform.model.Cliente;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class ClienteConfig {
    @Bean
    CommandLineRunner clienteCommandLineRunner(ClienteRepository repository) {
        return args -> {
            Cliente flavio = new Cliente(
                    "Flavio",
                    "Loreto",
                    "3958619432",
                    "Flavione@gmail.com",
                    "Via dei Flavionati 33",
                    LocalDate.of(1874, Month.DECEMBER, 1),
                    "FHDKSK45D94Z123D");
            Cliente michele = new Cliente(
                    "Giovanni",
                    "Casa",
                    "3958677432",
                    "Giovanni@gmail.com",
                    "Via dei Giovani 30",
                    LocalDate.of(2000, Month.JANUARY, 5),
                    "GHDKSK45D94Z123D");
            repository.deleteAll();
            repository.saveAll(List.of(flavio, michele));
        };
    }
}