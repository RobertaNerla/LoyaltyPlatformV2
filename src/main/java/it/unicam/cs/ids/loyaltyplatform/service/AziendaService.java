package it.unicam.cs.ids.loyaltyplatform.service;

import it.unicam.cs.ids.loyaltyplatform.dao.AziendaRepository;
import it.unicam.cs.ids.loyaltyplatform.exception.ResourceNotFoundException;
import it.unicam.cs.ids.loyaltyplatform.model.Azienda;
import it.unicam.cs.ids.loyaltyplatform.programmaFedelta.ProgrammaFedelta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AziendaService {
    @Autowired
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
        Optional<Azienda> aziendaOptional = aziendaRepository.findByNomeAndIndirizzo
                (azienda.getNome(), azienda.getIndirizzo());

        if (aziendaOptional.isPresent()) {
            throw new IllegalStateException("Azienda gia' presente!");
        }
        aziendaRepository.save(azienda);
    }

    //ToDo finire implementazione
    public void addProgrammaToAzienda(Long aziendaId, ProgrammaFedelta programmaFedelta) {
        Azienda azienda = aziendaRepository.findById(aziendaId)
                .orElseThrow(() -> new ResourceNotFoundException("Azienda con l'id " + aziendaId + " non trovato!"));

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
