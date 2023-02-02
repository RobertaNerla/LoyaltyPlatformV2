package it.unicam.cs.ids.loyaltyplatform.service;

import it.unicam.cs.ids.loyaltyplatform.dao.ProgrammaAPuntiRepository;
import it.unicam.cs.ids.loyaltyplatform.dao.ProgrammaFedeltaRepository;
import it.unicam.cs.ids.loyaltyplatform.model.ProgrammaAPunti;
import it.unicam.cs.ids.loyaltyplatform.model.ProgrammaFedelta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgrammaFedeltaService {
    @Autowired
    private final ProgrammaFedeltaRepository programmaFedeltaRepository;

    @Autowired
    public ProgrammaFedeltaService(ProgrammaFedeltaRepository progFedeltàRepository) {
        this.programmaFedeltaRepository = progFedeltàRepository;
    }

    /**
     * Restituisce tutti i programmi a punti gestiti nella piattaforma.
     *
     * @return una List<ProgrammaAPunti> contenente tutti programmi a punti salvati nel database.
     */
    public List<ProgrammaFedelta> getProgrammi() {
        return programmaFedeltaRepository.findAll();
    }
}
