package it.unicam.cs.ids.loyaltyplatform.convalida;

import it.unicam.cs.ids.loyaltyplatform.dto.TransazioneDto;
import it.unicam.cs.ids.loyaltyplatform.exception.ResourceNotFoundException;
import it.unicam.cs.ids.loyaltyplatform.programmaFedelta.ProgrammaFedeltaService;
import it.unicam.cs.ids.loyaltyplatform.sottoscrizione.Sottoscrizione;
import it.unicam.cs.ids.loyaltyplatform.sottoscrizione.SottoscrizioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GestoreConvalida {

    private final TransazioneService transazioneService;
    private final SottoscrizioneService sottoscrizioneService;

    private final List<Tracker> observers;
    @Autowired
    public GestoreConvalida(TransazioneController transazioneController, TransazioneService transazioneService, SottoscrizioneService sottoscrizioneService, ProgrammaFedeltaService programmaFedeltaService) {
        this.transazioneService = transazioneService;
        this.sottoscrizioneService = sottoscrizioneService;
        this.observers =new ArrayList<>(List.of(new TrackerSottoscrizioneProgAPunti(sottoscrizioneService.getSottoscrizioneRepository())));
    }


    public void convalidaTransazione(Long programId, TransazioneDto dto) throws ResourceNotFoundException {
        Sottoscrizione currentSub = sottoscrizioneService.getSottoscrizioneByProgramIdAndClientId(dto.getClienteId(),programId);
        Transazione newTransazione = transazioneService.addNewTransazione(dto);
        notifyAllObserver(currentSub, dto.getImporto());
    }

    public void notifyAllObserver(Sottoscrizione sottoscrizione, double importo){
        for(Tracker observer : observers){
            observer.update(sottoscrizione, importo);
        }
    }






}
