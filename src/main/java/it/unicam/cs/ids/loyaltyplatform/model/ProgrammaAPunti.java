package it.unicam.cs.ids.loyaltyplatform.model;

import it.unicam.cs.ids.loyaltyplatform.dto.ProgrammaAPuntiDto;
import jakarta.persistence.*;

/**
 * Classe che rappresenta un generico programma a punti gestito nella piattaforma.
 * Corrisponde anche all'omonima entità nel database del progetto.
 */

@Entity(name = "ProgrammaAPunti")
@Table(name = "programma_punti")
public class ProgrammaAPunti extends ProgrammaFedelta {

    @Column(name = "points_eur", nullable = false)
    private double pointsEur;

    public ProgrammaAPunti() {
    }

    public ProgrammaAPunti(ProgrammaAPuntiDto programmaAPuntiDto) {
        super(programmaAPuntiDto.getAziendaId(), programmaAPuntiDto.getName());
        this.pointsEur = programmaAPuntiDto.getPointsEur();
    }

    /**
     * Costruttore senza l'id del programma, perchè viene generato automaticamente, e senza
     * il num di clienti, dato che in fase di creazione il num di clienti di default è 0.
     *
     * @param aziendaId     identificatore dell'azienda che vuole creare il programma fedeltà
     * @param nomeProgramma nome del programma
     * @param pointsEur     rapporto points/eur
     */
    public ProgrammaAPunti(Long aziendaId, String nomeProgramma, double pointsEur) {
        super(aziendaId, nomeProgramma);
        this.pointsEur = pointsEur;
    }

    public ProgrammaAPunti(Long programId, Long aziendaId, String nomeProgramma, double pointsEur, int numClienti) {
        super(programId, aziendaId, nomeProgramma);
        this.pointsEur = pointsEur;
    }

    public double getPointsEur() {
        return pointsEur;
    }

    public void setPointsEur(double pointsEur) {
        this.pointsEur = pointsEur;
    }
}