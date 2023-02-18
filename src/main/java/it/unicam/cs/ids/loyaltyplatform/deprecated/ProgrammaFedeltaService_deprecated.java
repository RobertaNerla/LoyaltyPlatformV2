package it.unicam.cs.ids.loyaltyplatform.deprecated;

import it.unicam.cs.ids.loyaltyplatform.programmaFedelta.ProgrammaFedeltaRepository;
import it.unicam.cs.ids.loyaltyplatform.programmaFedelta.ProgrammaFedelta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgrammaFedeltaService_deprecated {
    private final ProgrammaFedeltaRepository programmaFedeltaRepository;

    @Autowired
    public ProgrammaFedeltaService_deprecated(ProgrammaFedeltaRepository progFedeltaRepository) {
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
