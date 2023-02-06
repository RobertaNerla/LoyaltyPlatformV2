package it.unicam.cs.ids.loyaltyplatform.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@ToString
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProgrammaFedelta that = (ProgrammaFedelta) o;
        return programId != null && Objects.equals(programId, that.programId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}