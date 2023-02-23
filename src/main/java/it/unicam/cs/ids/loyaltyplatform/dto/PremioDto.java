package it.unicam.cs.ids.loyaltyplatform.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PremioDto {
    @NotBlank
    private String nome;
    private String descrizione;
    @Min(value = 0)
    private int costoPunti;

    public PremioDto(String nome, String descrizione, int costoPunti) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.costoPunti = costoPunti;
    }

    public PremioDto() {
    }
}
