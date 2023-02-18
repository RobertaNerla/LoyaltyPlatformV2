package it.unicam.cs.ids.loyaltyplatform.sottoscrizione;

import it.unicam.cs.ids.loyaltyplatform.cliente.Cliente;
import it.unicam.cs.ids.loyaltyplatform.programmaFedelta.ProgrammaFedelta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class SottoscrizioneService {

    private final SottoscrizioneRepository sottoscrizioneRepository;

    private final SubFactory subFactory;
    @Autowired
    public SottoscrizioneService(SottoscrizioneRepository sottoscrizioneRepository, SubFactory subFactory) {
        this.sottoscrizioneRepository = sottoscrizioneRepository;
        this.subFactory = subFactory;
    }

    @GetMapping
    public List<Sottoscrizione> getSottoscrizioni(){
        return sottoscrizioneRepository.findAll();
    }
    @PostMapping
    public Sottoscrizione addNewSottoscrizione(Cliente cliente, ProgrammaFedelta programma) {
        Sottoscrizione newSub = subFactory.submit(cliente,programma);
        return sottoscrizioneRepository.save(newSub);
    }
}
