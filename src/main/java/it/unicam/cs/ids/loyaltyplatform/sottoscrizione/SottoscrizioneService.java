package it.unicam.cs.ids.loyaltyplatform.sottoscrizione;

import it.unicam.cs.ids.loyaltyplatform.cliente.Cliente;
import it.unicam.cs.ids.loyaltyplatform.cliente.ClienteService;
import it.unicam.cs.ids.loyaltyplatform.exception.ResourceNotFoundException;
import it.unicam.cs.ids.loyaltyplatform.programmaFedelta.ProgrammaFedelta;
import it.unicam.cs.ids.loyaltyplatform.programmaFedelta.ProgrammaFedeltaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Sottoscrizione> getSottoscrizioni() {
        return sottoscrizioneRepository.findAll();
    }

    public void addNewSottoscrizione(Long clienteId, Long programmaId) {
        Optional<Cliente> clienteOptional = clienteService.getClienteById(clienteId);
        Optional<ProgrammaFedelta> programmaOptional = programmaFedeltaService.getProgrammaById(programmaId);
        if (clienteOptional.isEmpty()) {
            throw new ResourceNotFoundException("Cliente con id " + clienteId + "non esiste!");
        } else if (programmaOptional.isEmpty()) {
            throw new ResourceNotFoundException("Programma fedeltà con id " + programmaId + "non esiste!");
        }

        Sottoscrizione newSub = subFactory.submit(clienteOptional.get(), programmaOptional.get());
        //TODO: Aggiungere la sottoscrizione su cliente e programma
        sottoscrizioneRepository.save(newSub);
    }
}
