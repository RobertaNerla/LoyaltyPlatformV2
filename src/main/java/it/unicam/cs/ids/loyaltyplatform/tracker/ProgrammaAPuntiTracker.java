package it.unicam.cs.ids.loyaltyplatform.tracker;

import it.unicam.cs.ids.loyaltyplatform.model.Cliente;
import it.unicam.cs.ids.loyaltyplatform.model.ProgrammaAPunti;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity(name = "ProgrammaAPuntiTracker")
@DiscriminatorValue("ProgrammaAPuntiTracker")
public class ProgrammaAPuntiTracker extends ProgrammaFedeltaTracker {
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
        super(cliente, programma);
        this.puntiAccumulati = puntiAccumulati;
    }

    /**
     * Costruttore con il cliente e il programma a cui esso si sottoscrive, i punti settati a 0
     *
     * @param cliente   cliente
     * @param programma programma a cui il cliente si sottoscrive
     */
    public ProgrammaAPuntiTracker(Cliente cliente, ProgrammaAPunti programma) {
        super(cliente, programma);
        this.puntiAccumulati = 0;
    }
}
