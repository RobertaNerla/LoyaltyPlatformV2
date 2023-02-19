package it.unicam.cs.ids.loyaltyplatform.convalida;

import it.unicam.cs.ids.loyaltyplatform.azienda.Azienda;
import it.unicam.cs.ids.loyaltyplatform.cliente.Cliente;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Getter
@Setter
@ToString
@Entity(name = "Transazione")
@Table(name = "transazione")
public class Transazione {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transazione_id",
            nullable = false,
            updatable = false)
    private Long transazioneId;

    @ManyToOne()
    @JoinColumn(name = "cliente_id", referencedColumnName = "cliente_id")
    private Cliente cliente;

    @ManyToOne()
    @JoinColumn(name = "azienda_id", referencedColumnName = "azienda_id")
    private Azienda azienda;

    @Column(name = "importo")
    private double importo;

    @Column(name = "data_transazione",columnDefinition = "DATE")
    private Date dataTransazione;

    /**
     * Costruttore di default per una transazione
     */
    public Transazione(){}

    /**
     * Costruttore in cui vengono passati il cliente che ha eseguito una transazione,
     * l'azienda che convalida la transazione e la data.
     * @param cliente cliente che ha effettuato la transazione
     * @param azienda azienda che convalida la transazione
     * @param dataTransazione data dell'avvenuta transazione
     */
    public Transazione(Cliente cliente, Azienda azienda, double importo, Date dataTransazione){
        this.cliente = cliente;
        this.azienda = azienda;
        this.importo = importo;
        this.dataTransazione = dataTransazione;
    }

    /**
     * Costruttore completo in cui viene passato come parametro anche l'id della transazione.
     * @param transazioneId id della transazione
     * @param cliente cliente che ha effettuato la transazione
     * @param azienda azienda che convalida la transazione
     * @param dataTransazione data dell'avvenuta transazione.
     */
    public Transazione(Long transazioneId, Cliente cliente, Azienda azienda, double importo, Date dataTransazione){
        this(cliente, azienda, importo,dataTransazione);
        this.transazioneId = transazioneId;
    }




}
