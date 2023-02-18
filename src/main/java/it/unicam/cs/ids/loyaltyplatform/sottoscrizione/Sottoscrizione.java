package it.unicam.cs.ids.loyaltyplatform.sottoscrizione;

import it.unicam.cs.ids.loyaltyplatform.cliente.Cliente;
import it.unicam.cs.ids.loyaltyplatform.programmaFedelta.ProgrammaAPunti;
import it.unicam.cs.ids.loyaltyplatform.programmaFedelta.ProgrammaFedelta;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity(name = "ProgrammaFedeltaTracker")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_tracker", discriminatorType = DiscriminatorType.STRING)
@Table(name = "programma_tracker",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"cliente_id", "programma_id"})})
public class Sottoscrizione {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tracker_id",
            nullable = false,
            updatable = false)
    private Long trackerId;

    @ManyToOne()
    @JoinColumn(name = "cliente_id", referencedColumnName = "cliente_id", foreignKey = @ForeignKey(name = "fk_cliente"))
    private Cliente cliente;
    
    @ManyToOne()
    @JoinColumn(name = "programma_id", referencedColumnName = "programma_id", foreignKey = @ForeignKey(name = "fk_programma_fedelta"))
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
    public Sottoscrizione(Long trackerId, Cliente cliente, ProgrammaAPunti programma) {
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
    public Sottoscrizione(Cliente cliente, ProgrammaAPunti programma) {
        this.cliente = cliente;
        this.programma = programma;
    }
}
