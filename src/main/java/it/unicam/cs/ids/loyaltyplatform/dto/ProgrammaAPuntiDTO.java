package it.unicam.cs.ids.loyaltyplatform.dto;

import it.unicam.cs.ids.loyaltyplatform.model.ProgrammaAPunti;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * La classe che rappresenta un Data Transfers Object di una classe ProgrammaAPunti
 */
@Getter
@Setter
@ToString
public class ProgrammaAPuntiDTO {

    @Min(value = 0)
    private Long aziendaId;
    @NotBlank
    private String name;
    @Min(value = 0)
    private double pointsEur;

    /**
     * Crea un nuovo ProgrammaAPuntiDTO.
     *
     * @param aziendaId l'id dell'azienda
     * @param name      il nome del programma
     * @param pointsEur il rateo di conversione tra punti ed euro
     */
    public ProgrammaAPuntiDTO(Long aziendaId, String name, double pointsEur) {
        this.aziendaId = aziendaId;
        this.name = name;
        this.pointsEur = pointsEur;
    }

    /**
     * Crea un nuovo ProgrammaAPuntiDTO.
     *
     * @param programmaAPunti il programma a punti
     */
    public ProgrammaAPuntiDTO(ProgrammaAPunti programmaAPunti) {
        this.aziendaId = programmaAPunti.getAziendaId();
        this.name = programmaAPunti.getNomeProgramma();
        this.pointsEur = programmaAPunti.getPointsEur();
    }
    /*
    public boolean isValid() {
        return pointsEur > 0 && !name.isBlank() && aziendaId > 0;
    }
     */
}
