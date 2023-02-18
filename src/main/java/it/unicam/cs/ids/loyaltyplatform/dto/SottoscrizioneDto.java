package it.unicam.cs.ids.loyaltyplatform.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SottoscrizioneDto {

    private Long clienteId;

    private Long programmaId;

    public SottoscrizioneDto(Long clienteId, Long programmaId) {
        this.clienteId = clienteId;
        this.programmaId = programmaId;
    }

}
