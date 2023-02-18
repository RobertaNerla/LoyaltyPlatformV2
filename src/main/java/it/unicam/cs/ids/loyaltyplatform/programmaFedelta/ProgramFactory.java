package it.unicam.cs.ids.loyaltyplatform.programmaFedelta;

import it.unicam.cs.ids.loyaltyplatform.dto.ProgrammaAPuntiDTO;
import it.unicam.cs.ids.loyaltyplatform.dto.ProgrammaFedeltaDto;
import it.unicam.cs.ids.loyaltyplatform.model.Azienda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ProgramFactory {
    @Autowired
    public ProgramFactory(){}

    public ProgrammaFedelta create(ProgrammaFedeltaDto dto, Azienda azienda, TipologiaProgramma tipo){
        if (Objects.requireNonNull(tipo) == TipologiaProgramma.punti) {
            if (dto instanceof ProgrammaAPuntiDTO puntiDto) {
                return new ProgrammaAPunti(puntiDto, azienda);
            } else {
                //gestione errore
                return null;
            }
            //da gestire se si voglio aggiungere altri tipi di programma fedeltà
        }
        return null;
    }
}
