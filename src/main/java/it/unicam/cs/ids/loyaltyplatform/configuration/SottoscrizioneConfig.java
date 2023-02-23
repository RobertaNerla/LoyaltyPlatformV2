package it.unicam.cs.ids.loyaltyplatform.configuration;

import it.unicam.cs.ids.loyaltyplatform.azienda.Azienda;
import it.unicam.cs.ids.loyaltyplatform.azienda.AziendaRepository;
import it.unicam.cs.ids.loyaltyplatform.cliente.Cliente;
import it.unicam.cs.ids.loyaltyplatform.cliente.ClienteRepository;
import it.unicam.cs.ids.loyaltyplatform.convalida.GestoreConvalida;
import it.unicam.cs.ids.loyaltyplatform.convalida.TransazioneRepository;
import it.unicam.cs.ids.loyaltyplatform.dto.TransazioneDto;
import it.unicam.cs.ids.loyaltyplatform.premio.Premio;
import it.unicam.cs.ids.loyaltyplatform.programmaFedelta.ProgrammaAPunti;
import it.unicam.cs.ids.loyaltyplatform.programmaFedelta.ProgrammaFedeltaRepository;
import it.unicam.cs.ids.loyaltyplatform.sottoscrizione.SottoscrizioneProgrammaAPunti;
import it.unicam.cs.ids.loyaltyplatform.sottoscrizione.SottoscrizioneRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class SottoscrizioneConfig {
    @Bean
    CommandLineRunner sottoscrizioneCommandLineRunner(ClienteRepository clienteRepository,
                                                      AziendaRepository aziendaRepository,
                                                      ProgrammaFedeltaRepository programmaFedeltaRepository,
                                                      SottoscrizioneRepository sottoscrizioneRepository,
                                                      TransazioneRepository transazioneRepository,
                                                      GestoreConvalida gestoreConvalida) {
        return args -> {
            clienteRepository.deleteAll();
            aziendaRepository.deleteAll();
            programmaFedeltaRepository.deleteAll();
            sottoscrizioneRepository.deleteAll();
            transazioneRepository.deleteAll();

            Cliente flavio = new Cliente(
                    "Flavio",
                    "Micheli",
                    "3958619432",
                    "Flavio@gmail.com",
                    "Via dei Magnifici 33",
                    LocalDate.of(1874, Month.DECEMBER, 1),
                    "FHDKSK45D94Z123D");
            Cliente michele = new Cliente(
                    "Michele",
                    "Casa",
                    "3958677432",
                    "michele.casa@gmail.com",
                    "Via Costanzo 30",
                    LocalDate.of(2000, Month.JANUARY, 5),
                    "GHDKSK45D94Z123D");
            Cliente massimo = new Cliente(
                    "Massimo",
                    "Decimo",
                    "3958677433",
                    "massimo.decimo@gmail.com",
                    "Viale della Repubblica 30",
                    LocalDate.of(2000, Month.JUNE, 24),
                    "DCMMSK45D94Z123D");

            Azienda sony = new Azienda("Sony", "Via sony 1", "GHFKDE345T5");
            Azienda openAi = new Azienda("openAi", "Via openAi 1", "GJFHEUDY432");

            ProgrammaAPunti programma1 = new ProgrammaAPunti(sony, "programma1", 3);
            ProgrammaAPunti programma2 = new ProgrammaAPunti(openAi, "programma2", 4);

            Premio premio = new Premio("Spazzolino", "spazzolino elettrico", 50, programma1);
            programma1.getCatalogoPremi().add(premio);

            SottoscrizioneProgrammaAPunti sottoscrizione1 = new SottoscrizioneProgrammaAPunti(flavio, programma1);
            SottoscrizioneProgrammaAPunti sottoscrizione2 = new SottoscrizioneProgrammaAPunti(michele, programma1);

            clienteRepository.saveAll(List.of(flavio, michele, massimo));
            aziendaRepository.saveAll(List.of(sony, openAi));
            programmaFedeltaRepository.saveAll(List.of(programma1, programma2));
            sottoscrizioneRepository.save(sottoscrizione1);
            sottoscrizioneRepository.save(sottoscrizione2);
            TransazioneDto transazione = new TransazioneDto(flavio.getCarta().getCartaId(), sony.getAziendaId(), 100);
            gestoreConvalida.convalidaTransazione(programma1.getProgrammaId(), transazione);
        };
    }
}