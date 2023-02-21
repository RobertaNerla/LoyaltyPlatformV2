package it.unicam.cs.ids.loyaltyplatform.deprecated;

import it.unicam.cs.ids.loyaltyplatform.cliente.Cliente;
import it.unicam.cs.ids.loyaltyplatform.cliente.ClienteService;
import it.unicam.cs.ids.loyaltyplatform.exception.ResourceNotFoundException;
import it.unicam.cs.ids.loyaltyplatform.programmaFedelta.ProgrammaAPunti;
import it.unicam.cs.ids.loyaltyplatform.sottoscrizione.SottoscrizioneProgrammaAPunti;
import it.unicam.cs.ids.loyaltyplatform.sottoscrizione.SottoscrizioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProgrammaAPuntiTrackerService_deprecated {

    SottoscrizioneRepository programmaAPuntiTrackerRepository;
    ProgrammaAPuntiService_deprecated programmaAPuntiService;
    ClienteService clienteService;

    @Autowired
    public ProgrammaAPuntiTrackerService_deprecated(SottoscrizioneRepository programmaAPuntiTrackerRepository,
                                                    ProgrammaAPuntiService_deprecated programmaAPuntiService,
                                                    ClienteService clienteService) {
        this.programmaAPuntiTrackerRepository = programmaAPuntiTrackerRepository;
        this.programmaAPuntiService = programmaAPuntiService;
        this.clienteService = clienteService;
    }

    /**
     * Iscrive un cliente a un programma a fedeltà a punti, restituendo l'apposito tracker
     *
     * @param cliente         cliente che sottoscrive il programma
     * @param programmaAPunti programma che il cliente sottoscrive
     * @return il tracker creato con i
     */
    public SottoscrizioneProgrammaAPunti addNewProgrammaAPuntiTracker(Cliente cliente, ProgrammaAPunti programmaAPunti) {
        Optional<Cliente> optionalCliente = clienteService.getClienteById(cliente.getClienteId());
        if (!optionalCliente.isPresent()) {
            throw new ResourceNotFoundException("Cliente with id " + cliente.getClienteId() + " could not be found");
        }
        Optional<ProgrammaAPunti> optionalProgramma = programmaAPuntiService.getProgrammaById(programmaAPunti.getProgrammaId());
        if (!optionalProgramma.isPresent()) {
            throw new ResourceNotFoundException("Reward program with id " + programmaAPunti.getProgrammaId() + " could not be found");
        }

        Cliente actualCliente = optionalCliente.get();
        ProgrammaAPunti actualProgramma = optionalProgramma.get();

        SottoscrizioneProgrammaAPunti tracker = new SottoscrizioneProgrammaAPunti(cliente, programmaAPunti);
        tracker.setCliente(actualCliente);
        tracker.setProgramma(actualProgramma);
        actualCliente.getSottoscrizioni().add(tracker);
        actualProgramma.getSottoscrizioni().add(tracker);
        programmaAPuntiService.aggiornaNumClienti(actualProgramma);

        programmaAPuntiTrackerRepository.save(tracker);
        return tracker;
    }
}
