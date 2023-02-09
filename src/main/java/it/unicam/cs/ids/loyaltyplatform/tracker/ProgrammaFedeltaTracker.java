package it.unicam.cs.ids.loyaltyplatform.tracker;

import it.unicam.cs.ids.loyaltyplatform.model.Cliente;
import it.unicam.cs.ids.loyaltyplatform.model.ProgrammaAPunti;
import it.unicam.cs.ids.loyaltyplatform.model.ProgrammaFedelta;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity(name = "ProgrammaFedeltaTracker")
@Table(name = "programma_tracker",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"cliente_id", "programma_id"})})
public class ProgrammaFedeltaTracker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tracker_id")
    private Long trackerId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id", referencedColumnName = "cliente_id")
    private Cliente cliente;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "programma_id", referencedColumnName = "programma_id")
    private ProgrammaFedelta programma;

    /**
     * Costruttore di default del generico tracker di programmi fedeltà
     */
    public ProgrammaFedeltaTracker() {
    }

    /**
     * Costruttore in cui viene passato come parametro anche l'id del tracker
     *
     * @param trackerId id del tracker
     * @param cliente   cliente che sottoscrive il programma fedeltà
     * @param programma programma a cui il cliente si sottoscrive
     */
    public ProgrammaFedeltaTracker(Long trackerId, Cliente cliente, ProgrammaAPunti programma) {
        this.trackerId = trackerId;
        this.cliente = cliente;
        this.programma = programma;
    }

    /**
     * Costruttore in cui l'id viene generato automaticamente
     *
     * @param cliente   cliente che sottocrive il programma fedeltà
     * @param programma programma a cui il cliente si sottoscrive
     */
    public ProgrammaFedeltaTracker(Cliente cliente, ProgrammaAPunti programma) {
        this.cliente = cliente;
        this.programma = programma;
    }
}
