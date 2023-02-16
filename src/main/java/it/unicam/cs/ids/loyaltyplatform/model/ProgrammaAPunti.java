package it.unicam.cs.ids.loyaltyplatform.model;

import it.unicam.cs.ids.loyaltyplatform.dto.ProgrammaAPuntiDTO;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Classe che rappresenta un generico programma a punti gestito nella piattaforma.
 * Corrisponde anche all'omonima entità nel database del progetto.
 */

@Getter
@Setter
@ToString
@Entity(name = "ProgrammaAPunti")
@DiscriminatorValue("ProgrammaAPunti")
public class ProgrammaAPunti extends ProgrammaFedelta {

    @Column(name = "points_eur", nullable = false)
    private double pointsEur;

    /**
     * Il costruttore di default di ProgrammaAPunti.
     */
    public ProgrammaAPunti() {
    }

    /**
     * Costruttore che istanzia un nuovo programma a punti a partire da un ProgrammaAPuntiDTO.
     *
     * @param programmaAPuntiDTO il DTO che contiene i parametri per la creazione di un oggetto ProgrammaAPunti.
     */

    public ProgrammaAPunti(ProgrammaAPuntiDTO programmaAPuntiDTO, Azienda azienda) {
        super(azienda, programmaAPuntiDTO.getNome());
        this.pointsEur = programmaAPuntiDTO.getPointsEur();
    }

    /**
     * Costruttore senza l'id del programma, perchè viene generato automaticamente, e senza
     * il num di clienti, dato che in fase di creazione il num di clienti di default è 0.
     *
     * @param azienda       azienda che vuole creare il programma fedeltà
     * @param nomeProgramma nome del programma
     * @param pointsEur     rapporto points/eur
     */
    public ProgrammaAPunti(Azienda azienda, String nomeProgramma, double pointsEur) {
        super(azienda, nomeProgramma);
        this.pointsEur = pointsEur;
    }

    /**
     * Costruttore con l'id del programma e tutti gli altri parametri.
     *
     * @param programId     l'id del programma.
     * @param azienda       azienda che crea il programma
     * @param nomeProgramma il nome del programma.
     * @param pointsEur     il rapporto points/eur.
     * @param numClienti    il numero di clienti del programma.
     */
    public ProgrammaAPunti(Long programId, Azienda azienda, String nomeProgramma, double pointsEur, int numClienti) {
        super(programId, azienda, nomeProgramma);
        this.pointsEur = pointsEur;
    }
}