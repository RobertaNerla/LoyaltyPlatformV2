package it.unicam.cs.ids.loyaltyplatform.programmaFedelta;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import it.unicam.cs.ids.loyaltyplatform.azienda.Azienda;
import it.unicam.cs.ids.loyaltyplatform.sottoscrizione.Sottoscrizione;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "tipo_programma",
        visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ProgrammaAPunti.class, name = "programma a punti")
})
@Entity(name = "ProgrammaFedelta")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_programma", discriminatorType = DiscriminatorType.STRING, columnDefinition = "programma generico")

@Table(name = "programma_fedelta")
public class ProgrammaFedelta {
    @Id
    @Column(name = "programma_id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long programmaId;

    @ManyToOne()
    @JoinColumn(name = "azienda_id", referencedColumnName = "azienda_id")
    private Azienda azienda;

    @Column(name = "nome_programma")
    private String nomeProgramma;

    @Column(name = "num_clienti", nullable = false)
    private int numClienti;
    @JsonIgnore
    @OneToMany(mappedBy = "programma", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Sottoscrizione> sottoscrizioni;


    /**
     * Costruttore di default del programma fedeltà
     */
    public ProgrammaFedelta() {
        sottoscrizioni = new ArrayList<>();
    }

    /**
     * Costruttore in cui viene passata l'azienda che ha creato il programma
     *
     * @param azienda       azienda che ha creato il programma fedeltò
     * @param nomeProgramma nome del programma fedeltà
     */
    public ProgrammaFedelta(Azienda azienda, String nomeProgramma) {
        this.azienda = azienda;
        this.nomeProgramma = nomeProgramma;
        this.numClienti = 0;
        sottoscrizioni = new ArrayList<>();
    }

    /**
     * Costruttore completo, in cui viene passato come parametro anche l'id del programma fedeltà
     *
     * @param programId     id del programma fedeltà
     * @param azienda       azienda che ha creato il programma fedeltà
     * @param nomeProgramma nome del programma fedeltà
     */
    public ProgrammaFedelta(Long programId, Azienda azienda, String nomeProgramma) {
        this(azienda, nomeProgramma);
        this.programmaId = programId;
        sottoscrizioni = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProgrammaFedelta that = (ProgrammaFedelta) o;
        return programmaId != null && Objects.equals(programmaId, that.programmaId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}