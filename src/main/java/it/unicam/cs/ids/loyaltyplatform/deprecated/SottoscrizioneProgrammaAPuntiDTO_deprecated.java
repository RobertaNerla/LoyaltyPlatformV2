package it.unicam.cs.ids.loyaltyplatform.deprecated;

import it.unicam.cs.ids.loyaltyplatform.cliente.Cliente;
import it.unicam.cs.ids.loyaltyplatform.programmaFedelta.ProgrammaAPunti;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class SottoscrizioneProgrammaAPuntiDTO_deprecated {

    @NotNull
    private ProgrammaAPunti programma;
    @NotNull
    private Cliente cliente;

    /**
     * Costruttore che crea un nuovo dto per il caso d'uso della SottoscrizioneDto
     *
     * @param programma programma a punti a cui ci si sottoscrive
     * @param cliente   cliente che si sottoscrive
     */
    public SottoscrizioneProgrammaAPuntiDTO_deprecated(ProgrammaAPunti programma, Cliente cliente) {
        this.programma = programma;
        this.cliente = cliente;
    }
}
