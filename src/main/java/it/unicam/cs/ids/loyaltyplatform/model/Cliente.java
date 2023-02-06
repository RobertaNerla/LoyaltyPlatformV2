package it.unicam.cs.ids.loyaltyplatform.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.Objects;

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

    @Column(name = "data_nascita")
    private LocalDate dataDiNascita;

    @Column(name = "codice_fiscale",
            nullable = false,
            columnDefinition = "TEXT")
    private String codiceFiscale;

    public Cliente() {
    }

    public Cliente(Long clienteId, String nome, String cognome, String numCellulare, String email, String indirizzo, LocalDate dataDiNascita, String codiceFiscale) {
        this.clienteId = clienteId;
        this.nome = nome;
        this.cognome = cognome;
        this.numCellulare = numCellulare;
        this.email = email;
        this.indirizzo = indirizzo;
        this.dataDiNascita = dataDiNascita;
        this.codiceFiscale = codiceFiscale;
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
    public Cliente(String nome, String cognome, String numCellulare, String email, String indirizzo, LocalDate dataDiNascita, String codiceFiscale) {
        this.nome = nome;
        this.cognome = cognome;
        this.numCellulare = numCellulare;
        this.email = email;
        this.indirizzo = indirizzo;
        this.dataDiNascita = dataDiNascita;
        this.codiceFiscale = codiceFiscale;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Cliente cliente = (Cliente) o;
        return clienteId != null && Objects.equals(clienteId, cliente.clienteId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}