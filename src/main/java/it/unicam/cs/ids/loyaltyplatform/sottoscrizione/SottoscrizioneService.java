package it.unicam.cs.ids.loyaltyplatform.sottoscrizione;

import it.unicam.cs.ids.loyaltyplatform.dao.SottoscrizioneRepository;
import it.unicam.cs.ids.loyaltyplatform.model.Cliente;
import it.unicam.cs.ids.loyaltyplatform.programmaFedelta.ProgrammaFedelta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class SottoscrizioneService {

    private final SottoscrizioneRepository sottoscrizioneRepository;

    private final SubsFactory subsFactory;
    @Autowired
    public SottoscrizioneService(SottoscrizioneRepository sottoscrizioneRepository, SubsFactory subsFactory) {
        this.sottoscrizioneRepository = sottoscrizioneRepository;
        this.subsFactory = subsFactory;
    }

    @GetMapping
    public List<Sottoscrizione> getSottoscrizioni(){
        return sottoscrizioneRepository.findAll();
    }
    @PostMapping
    public Sottoscrizione addNewSottoscrizione(Cliente cliente, ProgrammaFedelta programma) {
        Sottoscrizione newSub = subsFactory.submit(cliente,programma);
        return sottoscrizioneRepository.save(newSub);
    }
}
