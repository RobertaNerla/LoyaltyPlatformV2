package it.unicam.cs.ids.loyaltyplatform.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import it.unicam.cs.ids.loyaltyplatform.programmaFedelta.ProgrammaAPunti;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * La classe che rappresenta un Data Transfers Object di una classe ProgrammaAPunti
 */
@Getter
@Setter
@ToString
@JsonTypeName("punti")
public class ProgrammaAPuntiDTO implements ProgrammaFedeltaDto {
    @NotNull
    private Long aziendaId;
    @NotBlank
    private String nome;
    @Min(value = 0)
    private double pointsEur;

    /**
     * Crea un nuovo ProgrammaAPuntiDTO.
     *
     * @param aziendaId   Id dell'azienda che crea il programma
     * @param name      il nome del programma
     * @param pointsEur il rateo di conversione tra punti ed euro
     */
    public ProgrammaAPuntiDTO(Long aziendaId, String name, double pointsEur) {
        this.aziendaId = aziendaId;
        this.nome = name;
        this.pointsEur = pointsEur;
    }

    /**
     * Crea un nuovo ProgrammaAPuntiDTO.
     *
     * @param programmaAPunti il programma a punti
     */
    public ProgrammaAPuntiDTO(ProgrammaAPunti programmaAPunti) {
        this.aziendaId = programmaAPunti.getAzienda().getAziendaId();
        this.nome = programmaAPunti.getNomeProgramma();
        this.pointsEur = programmaAPunti.getPointsEur();
    }
}
