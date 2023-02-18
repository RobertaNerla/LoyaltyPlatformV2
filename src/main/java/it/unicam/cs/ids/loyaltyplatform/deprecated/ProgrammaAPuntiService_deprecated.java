package it.unicam.cs.ids.loyaltyplatform.deprecated;

import it.unicam.cs.ids.loyaltyplatform.programmaFedelta.ProgrammaFedeltaRepository;
import it.unicam.cs.ids.loyaltyplatform.dto.ProgrammaAPuntiDTO;
import it.unicam.cs.ids.loyaltyplatform.dto.ProgrammaFedeltaDto;
import it.unicam.cs.ids.loyaltyplatform.exception.ResourceNotFoundException;
import it.unicam.cs.ids.loyaltyplatform.azienda.Azienda;
import it.unicam.cs.ids.loyaltyplatform.programmaFedelta.ProgrammaAPunti;
import it.unicam.cs.ids.loyaltyplatform.programmaFedelta.ProgrammaFedelta;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Service
public class ProgrammaAPuntiService_deprecated {
    private final ProgrammaAPuntiRepository_deprecated programmaAPuntiRepository;

    private final ProgrammaFedeltaRepository repo;


    @Autowired
    public ProgrammaAPuntiService_deprecated(ProgrammaAPuntiRepository_deprecated programmaAPuntiRepository, ProgrammaFedeltaRepository repo) {
        this.programmaAPuntiRepository = programmaAPuntiRepository;
        this.repo = repo;
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
    public ProgrammaFedelta addNewProgrammaAPunti(@Validated ProgrammaFedeltaDto programmaAPuntiDto, Azienda azienda) {
        if(programmaAPuntiDto instanceof ProgrammaAPuntiDTO progPunti){
            ProgrammaAPunti programmaAPunti = new ProgrammaAPunti(progPunti, azienda);
            return repo.save(programmaAPunti);
        }
        else return null;
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
            throw new ResourceNotFoundException("Programma con id " + programId + " non esiste!");
        } else {
            programmaAPuntiRepository.deleteById(programId);
        }
    }

    /**
     * Endpoint per ottenere un programma a punti tramite il suo ID.
     *
     * @param programmaId ID del programma a punti
     * @return il programma a punti con l'ID specificato
     */
    public Optional<ProgrammaAPunti> getProgrammaById(Long programmaId) {
        return programmaAPuntiRepository.findById(programmaId);
    }

    /**
     * Aggiorna il numero dei clienti di un programma a punti di uno
     *
     * @param programmaAPunti l'id del programma da aggiornare
     */
    @Transactional
    public void aggiornaNumClienti(ProgrammaAPunti programmaAPunti) {
        programmaAPunti.setNumClienti(programmaAPunti.getNumClienti() + 1);
        programmaAPuntiRepository.save(programmaAPunti);
    }
}
