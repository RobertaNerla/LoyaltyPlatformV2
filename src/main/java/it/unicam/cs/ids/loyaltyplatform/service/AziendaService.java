package it.unicam.cs.ids.loyaltyplatform.service;

import it.unicam.cs.ids.loyaltyplatform.model.Azienda;
import it.unicam.cs.ids.loyaltyplatform.dao.AziendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AziendaService {
    public final AziendaRepository aziendaRepository;

    @Autowired
    public AziendaService(AziendaRepository aziendaRepository) {
        this.aziendaRepository = aziendaRepository;
    }

    public List<Azienda> getAziende() {
        return aziendaRepository.findAll();
    }

    public void addNewAzienda(Azienda azienda) {
        Optional<Azienda> aziendaOptional = aziendaRepository.findByNomeAndIndirizzo
                (azienda.getNome(), azienda.getIndirizzo());

        if (aziendaOptional.isPresent()) {
            throw new IllegalStateException("Azienda gia' presente!");
        }
        aziendaRepository.save(azienda);
    }

    public void deleteAzienda(Long aziendaId) {
        boolean exists = aziendaRepository.existsById(aziendaId);

        if (!exists) {
            throw new IllegalStateException("azienda con id " + aziendaId + " non esiste!");
        }
        aziendaRepository.deleteById(aziendaId);
    }
}
