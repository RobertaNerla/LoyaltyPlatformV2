package it.unicam.cs.ids.loyaltyplatform.service;

import it.unicam.cs.ids.loyaltyplatform.dao.ProgrammaAPuntiRepository;
import it.unicam.cs.ids.loyaltyplatform.dto.ProgrammaAPuntiDto;
import it.unicam.cs.ids.loyaltyplatform.model.ProgrammaAPunti;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgrammaAPuntiService {
    @Autowired
    private final ProgrammaAPuntiRepository progAPuntiRepository;

    @Autowired
    public ProgrammaAPuntiService(ProgrammaAPuntiRepository progAPuntiRepository) {
        this.progAPuntiRepository = progAPuntiRepository;
    }

    /**
     * Restituisce tutti i programmi a punti gestiti nella piattaforma.
     *
     * @return una List<ProgrammaAPunti> contenente tutti programmi a punti salvati nel database.
     */
    public List<ProgrammaAPunti> getProgAPunti(){
        return progAPuntiRepository.findAll();
    }

    /**
     * Aggiunge un nuovo programma a punti al database
     *
     * @param progAPuntiDto oggetto di trasferimento dati per un programma a punti.
     */

    public void addNewProgrammaAPunti(ProgrammaAPuntiDto progAPuntiDto){
        ProgrammaAPunti programmaAPunti = new ProgrammaAPunti(
                progAPuntiDto.getAziendaId(),
                progAPuntiDto.getName(),
                progAPuntiDto.getPointsEur());
        this.progAPuntiRepository.save(programmaAPunti);
    }

    public void deleteProgrammaAPunti(Long programId){
        boolean exists = this.progAPuntiRepository.existsById(programId);

        if(!exists){
            throw new IllegalStateException("Programma con id " + programId + " non esiste!");
        } else {
            this.progAPuntiRepository.deleteById(programId);
        }
    }
}
