package it.unicam.cs.ids.loyaltyplatform.sottoscrizione;

import it.unicam.cs.ids.loyaltyplatform.cliente.Cliente;
import it.unicam.cs.ids.loyaltyplatform.programmaFedelta.ProgrammaFedelta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubFactory {
    @Autowired
    public SubFactory(){}

    public Sottoscrizione submit(Cliente cliente, ProgrammaFedelta programma){
        //simile al factory per l'aggiunta del programma, bisognerebbe solo riuscire a capire il discriminante del programma, in teoria c'è
        //un modo per prenderlo...oppure bisognerebbe provare se
        //utilizzando l'instance of sul programma funziona
        //TODO: finire
        return null;
    }
}
