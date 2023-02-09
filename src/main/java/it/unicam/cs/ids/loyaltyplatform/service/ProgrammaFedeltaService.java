package it.unicam.cs.ids.loyaltyplatform.service;

import it.unicam.cs.ids.loyaltyplatform.dao.ProgrammaFedeltaRepository;
import it.unicam.cs.ids.loyaltyplatform.model.ProgrammaFedelta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgrammaFedeltaService {
    private final ProgrammaFedeltaRepository programmaFedeltaRepository;

    @Autowired
    public ProgrammaFedeltaService(ProgrammaFedeltaRepository progFedeltaRepository) {
        this.programmaFedeltaRepository = progFedeltaRepository;
    }

    /**
     * Restituisce tutti i programmi gestiti nella piattaforma.
     *
     * @return una List<ProgrammaFedelta> contenente tutti programmi a punti salvati nel database.
     */
    public List<ProgrammaFedelta> getProgrammi() {
        return programmaFedeltaRepository.findAll();
    }
}
