package it.unicam.cs.ids.loyaltyplatform.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SottoscrizioneDto {

    @NotNull
    private Long clienteId;
    @NotNull
    private Long programmaId;

    public SottoscrizioneDto(Long clienteId, Long programmaId) {
        this.clienteId = clienteId;
        this.programmaId = programmaId;
    }

}
