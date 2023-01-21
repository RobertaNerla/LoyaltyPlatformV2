package it.unicam.cs.ids.loyaltyplatform;

import it.unicam.cs.ids.loyaltyplatform.Entity.Cliente;
import it.unicam.cs.ids.loyaltyplatform.Repository.ClienteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class ClienteConfig {
    @Bean
    CommandLineRunner commandLineRunner(ClienteRepository repository) {
        return args -> {
            Cliente flavio = new Cliente(
                    "Flavio",
                    "Siumradini",
                    "3958619432",
                    "Flavione@gmail.com",
                    "Via dei Flavionati 33",
                    LocalDate.of(1874, Month.DECEMBER, 1),
                    "FHDKSK45D94Z123D");
            Cliente michele = new Cliente(
                    "Suchele",
                    "Casadidio",
                    "3958677432",
                    "Mariam@gmail.com",
                    "Via dei Tolentinati 30",
                    LocalDate.of(2000, Month.JANUARY, 5),
                    "GHDKSK45D94Z123D");
            //repository.deleteAll();
            //repository.saveAll(List.of(flavio, michele));
        };
    }
}