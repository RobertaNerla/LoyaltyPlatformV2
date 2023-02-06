package it.unicam.cs.ids.loyaltyplatform.service;

import it.unicam.cs.ids.loyaltyplatform.dao.ProgrammaAPuntiRepository;
import it.unicam.cs.ids.loyaltyplatform.dto.ProgrammaAPuntiDTO;
import it.unicam.cs.ids.loyaltyplatform.model.ProgrammaAPunti;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
public class ProgrammaAPuntiService {
    @Autowired
    private final ProgrammaAPuntiRepository programmaAPuntiRepository;

    @Autowired
    public ProgrammaAPuntiService(ProgrammaAPuntiRepository progAPuntiRepository) {
        this.programmaAPuntiRepository = progAPuntiRepository;
    }

    /**
     * Restituisce tutti i programmi a punti gestiti nella piattaforma.
     *
     * @return una List<ProgrammaAPunti> contenente tutti programmi a punti salvati nel database.
     */
    public List<ProgrammaAPunti> getProgrammaAPunti() {
        return programmaAPuntiRepository.findAll();
    }

    /**
     * Aggiunge un nuovo programma a punti al database
     *
     * @param programmaAPuntiDto oggetto di trasferimento dati per un programma a punti.
     */

    public ProgrammaAPunti addNewProgrammaAPunti(@Validated ProgrammaAPuntiDTO programmaAPuntiDto) {
        ProgrammaAPunti programmaAPunti = new ProgrammaAPunti(programmaAPuntiDto);
        return programmaAPuntiRepository.save(programmaAPunti);
    }

    public void deleteProgrammaAPunti(Long programId) {
        boolean exists = this.programmaAPuntiRepository.existsById(programId);

        if (!exists) {
            throw new IllegalStateException("Programma con id " + programId + " non esiste!");
        } else {
            this.programmaAPuntiRepository.deleteById(programId);
        }
    }
}
