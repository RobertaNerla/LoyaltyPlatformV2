package it.unicam.cs.ids.loyaltyplatform.cliente;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.unicam.cs.ids.loyaltyplatform.convalida.Transazione;
import it.unicam.cs.ids.loyaltyplatform.sottoscrizione.Sottoscrizione;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe che rappresenta un cliente nel sistema.
 * Corrisponde anche all'omonima entità nel database del progetto.
 */

@Getter
@Setter
@ToString
@Entity(name = "Cliente")
@Table(name = "cliente",
        uniqueConstraints = {
                @UniqueConstraint(name = "cliente_email_unique",
                        columnNames = "email"),
                @UniqueConstraint(name = "cliente_num_cellulare_unique",
                        columnNames = "num_cellulare")
        })
public class Cliente {
    @Id
    @Column(name = "cliente_id",
            nullable = false,
            updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long clienteId;

    @Column(name = "nome",
            nullable = false,
            columnDefinition = "TEXT")
    private String nome;

    @Column(name = "cognome",
            nullable = false,
            columnDefinition = "TEXT")
    private String cognome;

    @Column(name = "num_cellulare",
            nullable = false,
            columnDefinition = "TEXT")
    private String numCellulare;

    @Column(name = "email",
            nullable = false,
            columnDefinition = "TEXT")
    private String email;

    @Column(name = "indirizzo",
            nullable = false,
            columnDefinition = "TEXT")
    private String indirizzo;

    @Column(name = "data_nascita",
            columnDefinition = "DATE")
    private LocalDate dataDiNascita;

    @Column(name = "codice_fiscale",
            nullable = false,
            columnDefinition = "TEXT")
    private String codiceFiscale;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Sottoscrizione> programmiFedelta;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Transazione> transazioni;

    public Cliente() {
        programmiFedelta = new ArrayList<>();
    }

    public Cliente(Long clienteId, String nome, String cognome, String numCellulare, String email,
                   String indirizzo, LocalDate dataDiNascita, String codiceFiscale) {
        this.clienteId = clienteId;
        this.nome = nome;
        this.cognome = cognome;
        this.numCellulare = numCellulare;
        this.email = email;
        this.indirizzo = indirizzo;
        this.dataDiNascita = dataDiNascita;
        this.codiceFiscale = codiceFiscale;
        this.programmiFedelta = new ArrayList<>();
        this.transazioni = new ArrayList<>();
    }

    /**
     * Costrutore senza id, perchè quello viene generato automaticamente.
     *
     * @param nome          nome del cliente
     * @param cognome       cognome del cliente
     * @param numCellulare  numero di cellulare del cliente
     * @param email         email del cliente
     * @param indirizzo     indirizzo del cliente
     * @param dataDiNascita data di nascita del cliente
     * @param codiceFiscale codice fiscale del cliente
     */
    public Cliente(String nome, String cognome, String numCellulare, String email, String indirizzo,
                   LocalDate dataDiNascita, String codiceFiscale) {
        this.nome = nome;
        this.cognome = cognome;
        this.numCellulare = numCellulare;
        this.email = email;
        this.indirizzo = indirizzo;
        this.dataDiNascita = dataDiNascita;
        this.codiceFiscale = codiceFiscale;
        this.programmiFedelta = new ArrayList<>();
        this.transazioni = new ArrayList<>();
    }
}