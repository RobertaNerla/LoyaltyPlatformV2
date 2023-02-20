package it.unicam.cs.ids.loyaltyplatform.convalida;

import it.unicam.cs.ids.loyaltyplatform.exception.ResourceNotFoundException;
import it.unicam.cs.ids.loyaltyplatform.programmaFedelta.ProgrammaAPunti;
import it.unicam.cs.ids.loyaltyplatform.programmaFedelta.ProgrammaFedelta;
import it.unicam.cs.ids.loyaltyplatform.sottoscrizione.Sottoscrizione;
import it.unicam.cs.ids.loyaltyplatform.sottoscrizione.SottoscrizioneProgrammaAPunti;
import it.unicam.cs.ids.loyaltyplatform.sottoscrizione.SottoscrizioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TrackerSottoscrizioneProgAPunti implements Tracker {
    private final SottoscrizioneService sottoscrizioneService;

    @Autowired
    public TrackerSottoscrizioneProgAPunti(SottoscrizioneService sottoscrizioneService) {
        this.sottoscrizioneService = sottoscrizioneService;
    }

    @Override
    public void update(Sottoscrizione sottoscrizione, double importo) {
        if (sottoscrizione instanceof SottoscrizioneProgrammaAPunti subPunti) {
            double mol = getMoltiplicatorePunti(sottoscrizione.getProgramma());
            int newPunti = (int) (subPunti.getPuntiAccumulati() + (importo * mol));
            subPunti.setPuntiAccumulati(newPunti);
            sottoscrizioneService.saveChangedSottoscrizione(subPunti);
        }
    }

    private double getMoltiplicatorePunti(ProgrammaFedelta programma) throws ResourceNotFoundException {
        if (programma instanceof ProgrammaAPunti programmaAPunti) {
            return programmaAPunti.getPointsEur();
        } else {
            throw new ResourceNotFoundException("Errore di tipo");
        }
    }
}