package it.unicam.cs.ids.loyaltyplatform.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class SottoscrizioneProgrammaAPuntiDTO {

    @NotBlank
    private Long programmaId;
    @NotBlank
    private Long clienteId;

    /**
     * Costruttore che crea un nuovo dto per il caso d'uso della SottoscrizioneProgrammaAPunti
     *
     * @param programmaId l'id del programma a punti a cui ci si sottoscrive
     * @param clienteId   l'id del cliente che si sottoscrive
     */
    public SottoscrizioneProgrammaAPuntiDTO(Long programmaId, Long clienteId) {
        this.programmaId = programmaId;
        this.clienteId = clienteId;
    }
}
