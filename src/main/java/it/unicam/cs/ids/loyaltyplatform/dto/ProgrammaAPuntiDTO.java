package it.unicam.cs.ids.loyaltyplatform.dto;

import it.unicam.cs.ids.loyaltyplatform.model.ProgrammaAPunti;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class ProgrammaAPuntiDTO {

    @Min(value = 0)
    private Long aziendaId;
    @NotBlank
    private String name;
    @Min(value = 0)
    private double pointsEur;

    public ProgrammaAPuntiDTO(Long aziendaId, String name, double pointsEur) {
        this.aziendaId = aziendaId;
        this.name = name;
        this.pointsEur = pointsEur;
    }

    public ProgrammaAPuntiDTO(ProgrammaAPunti programmaAPunti) {
        this.aziendaId = programmaAPunti.getAziendaId();
        this.name = programmaAPunti.getNomeProgramma();
        this.pointsEur = programmaAPunti.getPointsEur();
    }

    public Long getAziendaId() {
        return aziendaId;
    }

    public void setAziendaId(Long aziendaId) {
        this.aziendaId = aziendaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPointsEur() {
        return pointsEur;
    }

    public void setPointsEur(double pointsEur) {
        this.pointsEur = pointsEur;
    }

    /*
    public boolean isValid() {
        return pointsEur > 0 && !name.isBlank() && aziendaId > 0;
    }
     */
}
