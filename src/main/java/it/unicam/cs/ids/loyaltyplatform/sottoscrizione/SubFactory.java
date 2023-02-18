package it.unicam.cs.ids.loyaltyplatform.sottoscrizione;

import it.unicam.cs.ids.loyaltyplatform.cliente.Cliente;
import it.unicam.cs.ids.loyaltyplatform.programmaFedelta.ProgrammaAPunti;
import it.unicam.cs.ids.loyaltyplatform.programmaFedelta.ProgrammaFedelta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubFactory {

    @Autowired
    public SubFactory(){}

    public Sottoscrizione submit(Cliente cliente, ProgrammaFedelta programma){

        if(programma instanceof ProgrammaAPunti progPunti){
                return new SottoscrizioneProgrammaAPunti(cliente, progPunti);
        } else {
            //Gestire altri tipi di programmi
            return null;
        }
    }

}
