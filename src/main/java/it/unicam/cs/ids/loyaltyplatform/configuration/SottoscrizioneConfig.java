package it.unicam.cs.ids.loyaltyplatform.configuration;

import it.unicam.cs.ids.loyaltyplatform.azienda.Azienda;
import it.unicam.cs.ids.loyaltyplatform.azienda.AziendaRepository;
import it.unicam.cs.ids.loyaltyplatform.azienda.AziendaService;
import it.unicam.cs.ids.loyaltyplatform.cliente.Cliente;
import it.unicam.cs.ids.loyaltyplatform.cliente.ClienteRepository;
import it.unicam.cs.ids.loyaltyplatform.cliente.ClienteService;
import it.unicam.cs.ids.loyaltyplatform.convalida.GestoreConvalida;
import it.unicam.cs.ids.loyaltyplatform.convalida.TransazioneRepository;
import it.unicam.cs.ids.loyaltyplatform.convalida.TransazioneService;
import it.unicam.cs.ids.loyaltyplatform.dto.PremioDto;
import it.unicam.cs.ids.loyaltyplatform.dto.ProgrammaAPuntiDTO;
import it.unicam.cs.ids.loyaltyplatform.dto.TransazioneDto;
import it.unicam.cs.ids.loyaltyplatform.premio.Premio;
import it.unicam.cs.ids.loyaltyplatform.programmaFedelta.ProgrammaAPunti;
import it.unicam.cs.ids.loyaltyplatform.programmaFedelta.ProgrammaFedelta;
import it.unicam.cs.ids.loyaltyplatform.programmaFedelta.ProgrammaFedeltaRepository;
import it.unicam.cs.ids.loyaltyplatform.programmaFedelta.ProgrammaFedeltaService;
import it.unicam.cs.ids.loyaltyplatform.sottoscrizione.Sottoscrizione;
import it.unicam.cs.ids.loyaltyplatform.sottoscrizione.SottoscrizioneProgrammaAPunti;
import it.unicam.cs.ids.loyaltyplatform.sottoscrizione.SottoscrizioneRepository;
import it.unicam.cs.ids.loyaltyplatform.sottoscrizione.SottoscrizioneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static it.unicam.cs.ids.loyaltyplatform.programmaFedelta.TipologiaProgramma.punti;

@Configuration
public class SottoscrizioneConfig {
    @Bean
    CommandLineRunner sottoscrizioneCommandLineRunner(ClienteService clienteService,
                                                      AziendaService aziendaService,
                                                      ProgrammaFedeltaService programmaFedeltaService,
                                                      SottoscrizioneService sottoscrizioneService,
                                                      TransazioneService transazioneService,
                                                      GestoreConvalida gestoreConvalida) {
        return args -> {
            clienteService.deleteAllClienti();
            aziendaService.deleteAllAziende();
            programmaFedeltaService.deleteAllProgrammi();
            sottoscrizioneService.deleteAllSottoscrizioni();
            transazioneService.deleteAllTransazioni();

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

            clienteService.addNewCliente(flavio);
            clienteService.addNewCliente(michele);
            clienteService.addNewCliente(massimo);

            Azienda sony = new Azienda("Sony", "Via sony 1", "GHFKDE345T5");
            Azienda openAi = new Azienda("openAi", "Via openAi 1", "GJFHEUDY432");

            aziendaService.addNewAzienda(sony);
            aziendaService.addNewAzienda(openAi);

            ProgrammaAPuntiDTO programma1Dto = new ProgrammaAPuntiDTO(sony.getAziendaId(),"programma1", 3);
            ProgrammaAPuntiDTO programma2Dto = new ProgrammaAPuntiDTO(openAi.getAziendaId(),"programma2", 4);

            programmaFedeltaService.addNewProgrammaFedelta(programma1Dto,punti);
            programmaFedeltaService.addNewProgrammaFedelta(programma2Dto,punti);

            ProgrammaFedelta programma1 = programmaFedeltaService.getProgrammiFedelta()
                    .stream()
                    .filter(p-> p.getNomeProgramma().equals("programma1"))
                    .findFirst().get();



            PremioDto premioDto = new PremioDto("Spazzolino", "Spazzolino elettrico", 50);
            programmaFedeltaService.aggiungiPremio(programma1.getProgrammaId(),premioDto);

            Sottoscrizione sottoscrizione1 = sottoscrizioneService.addNewSottoscrizione(flavio.getClienteId(),programma1.getProgrammaId());
            Sottoscrizione sottoscrizione2 = sottoscrizioneService.addNewSottoscrizione(michele.getClienteId(),programma1.getProgrammaId());

            TransazioneDto transazione = new TransazioneDto(flavio.getCarta().getCartaId(), sony.getAziendaId(), 100);
            gestoreConvalida.convalidaTransazione(programma1.getProgrammaId(), transazione);


        };
    }
}