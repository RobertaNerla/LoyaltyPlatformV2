package it.unicam.cs.ids.loyaltyplatform.azienda;

import it.unicam.cs.ids.loyaltyplatform.exception.ResourceAlreadyExistsException;
import it.unicam.cs.ids.loyaltyplatform.exception.ResourceNotFoundException;
import it.unicam.cs.ids.loyaltyplatform.programmaFedelta.ProgrammaFedelta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AziendaService {
    private final AziendaRepository aziendaRepository;

    @Autowired
    public AziendaService(AziendaRepository aziendaRepository) {
        this.aziendaRepository = aziendaRepository;
    }


    /**
     * Restituisce tutte le aziende presenti nel database
     *
     * @return una List<Azienda> contenente tutte le aziende presenti nel database
     */
    public List<Azienda> getAziende() {
        return aziendaRepository.findAll();
    }

    /**
     * Aggiunge una nuova azienda al database
     *
     * @param azienda azienda che si vuole aggiungere nel database
     */
    public void addNewAzienda(Azienda azienda) {

        Optional<Azienda> aziendaOptional = aziendaRepository.findByNomeAndIndirizzo(azienda.getNome(), azienda.getIndirizzo());

        if (aziendaOptional.isPresent()) {
            throw new ResourceAlreadyExistsException("Azienda con nome " + azienda.getAziendaId() + " e indirizzo " +
                    azienda.getIndirizzo() + " gia' presente!");
        }
        aziendaRepository.save(azienda);
    }

    public void addProgrammaToAzienda(Long aziendaId, ProgrammaFedelta programmaFedelta) {
        Optional<Azienda> aziendaOptional = aziendaRepository.findById(aziendaId);
        if (aziendaOptional.isEmpty()) {
            throw new ResourceNotFoundException("Azienda con l'id " + aziendaId + " non trovata!");
        }

        boolean exists = aziendaOptional.get().getProgrammiFedelta().stream()
                .anyMatch(pf -> pf.getNomeProgramma().equals(programmaFedelta.getNomeProgramma()));
        if (exists) {
            throw new ResourceAlreadyExistsException("Un programma fedeltà con il nome" +
                    programmaFedelta.getNomeProgramma() + " appartiene già all'azienda");
        }

        if (programmaFedelta.getAzienda().getAziendaId() != aziendaId) {
            throw new IllegalArgumentException("Il programma fedeltà non appartiene a questa azienda, ma a " +
                    programmaFedelta.getAzienda().toString());
        }

        aziendaOptional.get().getProgrammiFedelta().add(programmaFedelta);
    }

    /**
     * Elimina un'azienda con lo specifico aziendaId dal database
     *
     * @param aziendaId id dell'azienda che si vuole eliminare dal database
     */
    public void deleteAzienda(Long aziendaId) {
        boolean exists = aziendaRepository.existsById(aziendaId);

        if (!exists) {
            throw new ResourceNotFoundException("azienda con id " + aziendaId + " non esiste!");
        } else {
            aziendaRepository.deleteById(aziendaId);
        }
    }

    public Optional<Azienda> getAziendaById(long aziendaId) {
        return aziendaRepository.findById(aziendaId);
    }
}
