package it.unicam.cs.ids.loyaltyplatform.configuration;

import it.unicam.cs.ids.loyaltyplatform.dao.AziendaRepository;
import it.unicam.cs.ids.loyaltyplatform.dao.ProgrammaFedeltaRepository;
import it.unicam.cs.ids.loyaltyplatform.model.Azienda;
import it.unicam.cs.ids.loyaltyplatform.model.ProgrammaAPunti;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AziendaAndProgrammaFedeltaConfig {
    @Bean
    CommandLineRunner aziendaCommandLineRunner(AziendaRepository aziendaRepository,
                                               ProgrammaFedeltaRepository programmaFedeltaRepository) {
        return args -> {
            Azienda sony = new Azienda(
                    "Sony",
                    "Via sony 1",
                    "partitaIva1");
            Azienda openAi = new Azienda(
                    "openAi",
                    "Via openAi 1",
                    "partitaIva2");
            ProgrammaAPunti programma1 = new ProgrammaAPunti(sony, "programma1", 3);
            ProgrammaAPunti programma2 = new ProgrammaAPunti(openAi, "programma2", 3);
            sony.getProgrammiFedelta().add(programma1);
            openAi.getProgrammiFedelta().add(programma2);
            aziendaRepository.deleteAll();
            programmaFedeltaRepository.deleteAll();
            programmaFedeltaRepository.saveAll(List.of(programma1, programma2));
            aziendaRepository.saveAll(List.of(sony, openAi));
        };
    }
}