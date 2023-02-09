package it.unicam.cs.ids.loyaltyplatform.dto;

import it.unicam.cs.ids.loyaltyplatform.model.Azienda;
import it.unicam.cs.ids.loyaltyplatform.model.ProgrammaAPunti;
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
public class ProgrammaAPuntiDTO {
    @NotNull
    private Azienda azienda;
    @NotBlank
    private String nome;
    @Min(value = 0)
    private double pointsEur;

    /**
     * Crea un nuovo ProgrammaAPuntiDTO.
     *
     * @param azienda   azienda che crea il programma
     * @param name      il nome del programma
     * @param pointsEur il rateo di conversione tra punti ed euro
     */
    public ProgrammaAPuntiDTO(Azienda azienda, String name, double pointsEur) {
        this.azienda = azienda;
        this.nome = name;
        this.pointsEur = pointsEur;
    }

    /**
     * Crea un nuovo ProgrammaAPuntiDTO.
     *
     * @param programmaAPunti il programma a punti
     */
    public ProgrammaAPuntiDTO(ProgrammaAPunti programmaAPunti) {
        this.azienda = programmaAPunti.getAzienda();
        this.nome = programmaAPunti.getNomeProgramma();
        this.pointsEur = programmaAPunti.getPointsEur();
    }
}
