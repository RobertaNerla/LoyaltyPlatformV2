package it.unicam.cs.ids.loyaltyplatform.tracker;

import it.unicam.cs.ids.loyaltyplatform.model.Cliente;
import it.unicam.cs.ids.loyaltyplatform.model.ProgrammaAPunti;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity(name = "ProgrammaAPuntiTracker")
@Table(name = "programma_punti_tracker",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"cliente_id", "programma_id"})})
public class ProgrammaAPuntiTracker implements ProgrammaFedeltaTracker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tracker_id")
    private Long trackerId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id", referencedColumnName = "cliente_id")
    private Cliente cliente;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "programma_id", referencedColumnName = "programma_id")
    private ProgrammaAPunti programma;
    @Column(name = "punti_accumulati", nullable = false)
    private int puntiAccumulati;

    /**
     * Costruttore di default
     */
    public ProgrammaAPuntiTracker() {
    }

    /**
     * Costruttore che prendi come parametri tutti gli attributi, tolto l'id del tracker
     *
     * @param cliente         cliente che sottoscrive il programma
     * @param programma       programma sottoscritto dal cliente
     * @param puntiAccumulati punti che il cliente accumula
     */
    public ProgrammaAPuntiTracker(Cliente cliente, ProgrammaAPunti programma, int puntiAccumulati) {
        this.cliente = cliente;
        this.programma = programma;
        this.puntiAccumulati = puntiAccumulati;
    }

    /**
     * Costruttore con il cliente e il programma a cui esso si sottoscrive, i punti settati a 0
     *
     * @param cliente   cliente
     * @param programma programma a cui il cliente si sottoscrive
     */
    public ProgrammaAPuntiTracker(Cliente cliente, ProgrammaAPunti programma) {
        this.cliente = cliente;
        this.programma = programma;
        this.puntiAccumulati = 0;
    }
}
