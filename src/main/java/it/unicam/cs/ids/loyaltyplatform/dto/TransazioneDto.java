package it.unicam.cs.ids.loyaltyplatform.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
@Getter
@Setter
@ToString
public class TransazioneDto {
    @NotNull
    private Long clienteId;
    @NotNull
    private Long aziendaId;
    @NotNull
    private double importo;


    /**
     * Crea una nuova TransazioneDto.
     *
     * @param clienteId Id del cliente che esegue la transazione
     * @param aziendaId Id dell'azienda che convalida la transazione
     * @param importo importo della transazione
     */
    public TransazioneDto(Long clienteId, Long aziendaId, double importo) {
        this.clienteId = clienteId;
        this.aziendaId = aziendaId;
        this.importo = importo;
    }
}
