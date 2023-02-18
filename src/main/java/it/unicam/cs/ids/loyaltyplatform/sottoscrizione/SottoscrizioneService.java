package it.unicam.cs.ids.loyaltyplatform.sottoscrizione;

import it.unicam.cs.ids.loyaltyplatform.cliente.Cliente;
import it.unicam.cs.ids.loyaltyplatform.cliente.ClienteService;
import it.unicam.cs.ids.loyaltyplatform.exception.ResourceNotFoundException;
import it.unicam.cs.ids.loyaltyplatform.programmaFedelta.ProgrammaFedelta;
import it.unicam.cs.ids.loyaltyplatform.programmaFedelta.ProgrammaFedeltaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Service
public class SottoscrizioneService {
    private final SottoscrizioneRepository sottoscrizioneRepository;
    private final ClienteService clienteService;
    private final ProgrammaFedeltaService programmaFedeltaService;
    private final SubFactory subFactory;

    @Autowired
    public SottoscrizioneService(SottoscrizioneRepository sottoscrizioneRepository,
                                 ClienteService clienteService,
                                 ProgrammaFedeltaService programmaFedeltaService,
                                 SubFactory subFactory) {
        this.sottoscrizioneRepository = sottoscrizioneRepository;
        this.clienteService = clienteService;
        this.programmaFedeltaService = programmaFedeltaService;
        this.subFactory = subFactory;
    }

    @GetMapping
    public List<Sottoscrizione> getSottoscrizioni() {
        return sottoscrizioneRepository.findAll();
    }

    @PostMapping
    public Sottoscrizione addNewSottoscrizione(Long clienteId, Long programmaId) throws ResourceNotFoundException {
        Cliente currentClient = getClienteById(clienteId);
        ProgrammaFedelta currentProgram = getProgrammaById(programmaId);
        Sottoscrizione newSub = subFactory.submit(currentClient, currentProgram);
        //TODO: Aggiungere la sottoscrizione su cliente e programma
        upgradeSottoscrizioniCliente(currentClient, newSub);
        incrementaNumClienti(currentProgram);
        return sottoscrizioneRepository.save(newSub);
    }

    private Cliente getClienteById(Long clienteId) {
        Optional<Cliente> optionalCliente = clienteService.getClienteById(clienteId);
        if (optionalCliente.isEmpty()) {
            throw new ResourceNotFoundException("Cliente con id " + clienteId + "non esiste!");
        } else {
            return optionalCliente.get();
        }
    }

    private ProgrammaFedelta getProgrammaById(Long programmaId) {
        Optional<ProgrammaFedelta> optionalProgramma = programmaFedeltaService.getProgrammaById(programmaId);
        if (optionalProgramma.isEmpty()) {
            throw new ResourceNotFoundException("Programma fedeltà con id " + programmaId + "non esiste!");
        } else {
            return optionalProgramma.get();
        }
    }

    private void upgradeSottoscrizioniCliente(Cliente cliente, Sottoscrizione newSub) {
        clienteService.aggiungiNuovaSottoscrizione(cliente, newSub);
    }

    private void incrementaNumClienti(ProgrammaFedelta programma) {
        programmaFedeltaService.incrementaNumClienti(programma);
    }
}
