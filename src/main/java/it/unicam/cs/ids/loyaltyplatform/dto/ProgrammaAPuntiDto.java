package it.unicam.cs.ids.loyaltyplatform.dto;

public class ProgrammaAPuntiDto {

    private Long aziendaId;
    private String name;
    private double pointsEur;

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
}
