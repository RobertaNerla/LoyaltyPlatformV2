package it.unicam.cs.ids.loyaltyplatform.convalida;

import it.unicam.cs.ids.loyaltyplatform.carta.CartaService;
import it.unicam.cs.ids.loyaltyplatform.cliente.Cliente;
import it.unicam.cs.ids.loyaltyplatform.dto.TransazioneDto;
import it.unicam.cs.ids.loyaltyplatform.exception.ResourceNotFoundException;
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

    private final CartaService cartaService;
    private final List<Tracker> observers;

    @Autowired
    public GestoreConvalida(TransazioneService transazioneService,
                            SottoscrizioneService sottoscrizioneService,
                            CartaService cartaService, TrackerSottoscrizioneProgAPunti trackerSottoscrizioneProgAPunti) {
        this.transazioneService = transazioneService;
        this.sottoscrizioneService = sottoscrizioneService;
        this.cartaService = cartaService;
        this.observers = new ArrayList<>(List.of(trackerSottoscrizioneProgAPunti));
    }

    public Sottoscrizione convalidaTransazione(Long programId, TransazioneDto dto) throws ResourceNotFoundException {
        Cliente currentCliente = cartaService.getClienteByIdCarta(dto.getCartaId());
        Sottoscrizione currentSub = sottoscrizioneService.getSottoscrizioneByProgramIdAndClientId(programId, currentCliente);
        transazioneService.addNewTransazione(dto,currentCliente);
        notifyAllObservers(currentSub, dto.getImporto());
        return sottoscrizioneService.getSottoscrizioneByProgramIdAndClientId(programId, currentCliente);
    }

    public void notifyAllObservers(Sottoscrizione sottoscrizione, double importo) {
        for (Tracker observer : observers) {
            observer.update(sottoscrizione, importo);
        }
    }
}