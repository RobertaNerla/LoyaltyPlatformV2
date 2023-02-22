package it.unicam.cs.ids.loyaltyplatform.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartaDto {
    @NotNull
    private Long clienteId;

    public CartaDto(Long clienteId) {
        this.clienteId = clienteId;
    }

    public CartaDto() {
    }
}
