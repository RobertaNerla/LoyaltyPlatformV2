package it.unicam.cs.ids.loyaltyplatform.sottoscrizione;

import it.unicam.cs.ids.loyaltyplatform.cliente.Cliente;
import it.unicam.cs.ids.loyaltyplatform.programmaFedelta.ProgrammaFedelta;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@ToString
@Entity(name = "Sottoscrizione")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_tracker", discriminatorType = DiscriminatorType.STRING)
@Table(name = "sottoscrizione")
public class Sottoscrizione {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sottoscrizione_id",
            nullable = false,
            updatable = false)
    private Long sottoscrizioneId;

    @ManyToOne()
    @JoinColumn(name = "cliente_id", referencedColumnName = "cliente_id")
    private Cliente cliente;

    @ManyToOne()
    @JoinColumn(name = "programma_id", referencedColumnName = "programma_id")
    private ProgrammaFedelta programma;

    /**
     * Costruttore di default del generico tracker di programmi fedeltà
     */
    public Sottoscrizione() {
    }

    /**
     * Costruttore in cui viene passato come parametro anche l'id del tracker
     *
     * @param trackerId id del tracker
     * @param cliente   cliente che sottoscrive il programma fedeltà
     * @param programma programma a cui il cliente si sottoscrive
     */
    public Sottoscrizione(Long trackerId, Cliente cliente, ProgrammaFedelta programma) {
        this.sottoscrizioneId = trackerId;
        this.cliente = cliente;
        this.programma = programma;
    }

    /**
     * Costruttore in cui l'id viene generato automaticamente
     *
     * @param cliente   cliente che sottocrive il programma fedeltà
     * @param programma programma a cui il cliente si sottoscrive
     */
    public Sottoscrizione(Cliente cliente, ProgrammaFedelta programma) {
        this.cliente = cliente;
        this.programma = programma;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Sottoscrizione that = (Sottoscrizione) o;
        return sottoscrizioneId != null && Objects.equals(sottoscrizioneId, that.sottoscrizioneId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}