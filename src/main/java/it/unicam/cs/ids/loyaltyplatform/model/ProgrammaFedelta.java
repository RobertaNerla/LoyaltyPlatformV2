package it.unicam.cs.ids.loyaltyplatform.model;

import jakarta.persistence.*;

@Entity(name = "ProgrammaFedelta")
@Table(name = "programma_fedelta")
public class ProgrammaFedelta {
    @Id
    @Column(name = "program_id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long programId;

    @Column(name = "azienda_id", nullable = false, updatable = false)
    private Long aziendaId;

    @Column(name = "nome_programma")
    private String nomeProgramma;

    @Column(name = "num_clienti", nullable = false)
    private int numClienti;

    public ProgrammaFedelta() {

    }

    public ProgrammaFedelta(Long aziendaId, String nomeProgramma) {
        this.aziendaId = aziendaId;
        this.nomeProgramma = nomeProgramma;
        this.numClienti = 0;
    }

    public ProgrammaFedelta(Long programId, Long aziendaId, String nomeProgramma) {
        this(aziendaId, nomeProgramma);
        this.programId = programId;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public Long getAziendaId() {
        return aziendaId;
    }

    public void setAziendaId(Long aziendaId) {
        this.aziendaId = aziendaId;
    }

    public String getNomeProgramma() {
        return nomeProgramma;
    }

    public void setNomeProgramma(String nomeProgramma) {
        this.nomeProgramma = nomeProgramma;
    }

    public int getNumClienti() {
        return numClienti;
    }

    public void setNumClienti(int numClienti) {
        this.numClienti = numClienti;
    }
}
