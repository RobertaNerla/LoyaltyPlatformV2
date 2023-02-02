package it.unicam.cs.ids.loyaltyplatform.model;

import it.unicam.cs.ids.loyaltyplatform.dto.ProgrammaAPuntiDto;
import jakarta.persistence.*;

/**
 * Classe che rappresenta un generico programma a punti gestito nella piattaforma.
 * Corrisponde anche all'omonima entità nel database del progetto.
 */

@Entity(name = "ProgrammaAPunti")
@Table(name = "programma_punti")
public class ProgrammaAPunti {

    @Id
    @Column(name = "program_id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long programId;

    //TODO: chiave?
    @Column(name = "azienda_id", nullable = false, updatable = false)
    private Long aziendaId;

    @Column(name = "nome_programma")
    private String nomeProgramma;
    @Column(name = "points_eur", nullable = false)
    private double pointsEur;

    @Column(name = "num_clienti", nullable = false)
    private int numClienti;

    public ProgrammaAPunti() {
    }

    public ProgrammaAPunti(ProgrammaAPuntiDto programmaAPuntiDto) {
        this.aziendaId = programmaAPuntiDto.getAziendaId();
        this.nomeProgramma = programmaAPuntiDto.getName();
        this.pointsEur = programmaAPuntiDto.getPointsEur();
        numClienti = 0;
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
        this.aziendaId = aziendaId;
        this.nomeProgramma = nomeProgramma;
        this.pointsEur = pointsEur;
        numClienti = 0;
    }

    public ProgrammaAPunti(Long programId, Long aziendaId, String nomeProgramma, double pointsEur, int numClienti) {
        this(aziendaId, nomeProgramma, pointsEur);
        this.programId = programId;
        this.numClienti = numClienti;
    }


    public Long getProgramId() {
        return programId;
    }

    public Long getAziendaId() {
        return aziendaId;
    }

    public String getNomeProgramma() {
        return nomeProgramma;
    }

    public void setNomeProgramma(String nomeProgramma) {
        this.nomeProgramma = nomeProgramma;
    }

    public double getPointsEur() {
        return pointsEur;
    }

    public void setPointsEur(double pointsEur) {
        this.pointsEur = pointsEur;
    }

    public int getNumClienti() {
        return numClienti;
    }

    public void setNumClienti(int numClienti) {
        this.numClienti = numClienti;
    }
}
