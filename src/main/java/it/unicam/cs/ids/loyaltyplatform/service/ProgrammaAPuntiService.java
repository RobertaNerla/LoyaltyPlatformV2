package it.unicam.cs.ids.loyaltyplatform.service;

import it.unicam.cs.ids.loyaltyplatform.dao.ProgrammaAPuntiRepository;
import it.unicam.cs.ids.loyaltyplatform.dto.ProgrammaAPuntiDTO;
import it.unicam.cs.ids.loyaltyplatform.exception.ResourceNotFoundException;
import it.unicam.cs.ids.loyaltyplatform.model.ProgrammaAPunti;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class ProgrammaAPuntiService {
    private final ProgrammaAPuntiRepository programmaAPuntiRepository;

    @Autowired
    public ProgrammaAPuntiService(ProgrammaAPuntiRepository programmaAPuntiRepository) {
        this.programmaAPuntiRepository = programmaAPuntiRepository;
    }

    /**
     * Restituisce tutti i programmi a punti gestiti nella piattaforma.
     *
     * @return una List<ProgrammaAPunti> contenente tutti programmi a punti salvati nel database.
     */
    @GetMapping
    public List<ProgrammaAPunti> getProgrammiAPunti() {
        return programmaAPuntiRepository.findAll();
    }

    /**
     * Aggiunge un nuovo programma a punti al database
     *
     * @param programmaAPuntiDto oggetto di trasferimento dati per un programma a punti.
     */

    @PostMapping
    public ProgrammaAPunti addNewProgrammaAPunti(@Validated ProgrammaAPuntiDTO programmaAPuntiDto) {
        ProgrammaAPunti programmaAPunti = new ProgrammaAPunti(programmaAPuntiDto);
        return programmaAPuntiRepository.save(programmaAPunti);
    }

    /**
     * Esegue una chiamata DELETE che elimina un programma a punti con lo specifico id dal database
     *
     * @param programId ID del programma a punti da eliminare
     * @throws IllegalStateException se il programma a punti non esiste
     */
    @DeleteMapping
    public void deleteProgrammaAPunti(Long programId) {
        boolean exists = programmaAPuntiRepository.existsById(programId);

        if (!exists) {
            throw new IllegalStateException("Programma con id " + programId + " non esiste!");
        } else {
            programmaAPuntiRepository.deleteById(programId);
        }
    }

    /**
     * Endpoint per ottenere un programma a punti tramite il suo ID.
     *
     * @param programmaId ID del programma a punti
     * @return il programma a punti con l'ID specificato
     * @throws ResourceNotFoundException se il programma a punti non viene trovato
     */
    public ProgrammaAPunti getProgramById(Long programmaId) {
        return programmaAPuntiRepository.findById(programmaId)
                .orElseThrow(() -> new ResourceNotFoundException("Programma a punti con l'id " + programmaId + " non trovato!"));
    }

    /**
     * Aggiorna il numero dei clienti di un programma a punti di uno
     *
     * @param programmaId l'id del programma da aggiornare
     */
    @Transactional
    public void aggiornaNumClienti(Long programmaId) {
        ProgrammaAPunti programmaAPunti = getProgramById(programmaId);
        programmaAPunti.setNumClienti(programmaAPunti.getNumClienti() + 1);
        programmaAPuntiRepository.save(programmaAPunti);
    }
}
