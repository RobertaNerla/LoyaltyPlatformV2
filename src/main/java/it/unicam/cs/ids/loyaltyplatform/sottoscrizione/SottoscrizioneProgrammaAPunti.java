package it.unicam.cs.ids.loyaltyplatform.sottoscrizione;

import it.unicam.cs.ids.loyaltyplatform.cliente.Cliente;
import it.unicam.cs.ids.loyaltyplatform.programmaFedelta.ProgrammaAPunti;
import it.unicam.cs.ids.loyaltyplatform.programmaFedelta.ProgrammaFedelta;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity(name = "SottoscrizioneProgrammaPunti")
@DiscriminatorValue("SottoscrizioneProgrammaPunti")
public class SottoscrizioneProgrammaAPunti extends Sottoscrizione {
    @Column(name = "punti_accumulati", nullable = false)
    private int puntiAccumulati;

    /**
     * Costruttore di default
     */
    public SottoscrizioneProgrammaAPunti() {}

    /**
     * Costruttore che prendi come parametri tutti gli attributi, tolto l'id del tracker
     *
     * @param cliente         cliente che sottoscrive il programma
     * @param programma       programma sottoscritto dal cliente
     * @param puntiAccumulati punti che il cliente accumula
     */
    public SottoscrizioneProgrammaAPunti(Cliente cliente, ProgrammaAPunti programma, int puntiAccumulati) {
        super(cliente, programma);
        this.puntiAccumulati = puntiAccumulati;
    }

    /**
     * Costruttore con il cliente e il programma a cui esso si sottoscrive, i punti settati a 0
     *
     * @param cliente   cliente
     * @param programma programma a cui il cliente si sottoscrive
     */
    public SottoscrizioneProgrammaAPunti(Cliente cliente, ProgrammaAPunti programma) {
        super(cliente, programma);
        this.puntiAccumulati = 0;
    }
}
