package it.unicam.cs.ids.loyaltyplatform.model;

import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * Classe che rappresenta un cliente nel sistema.
 * Corrisponde anche all'omonima entità nel database del progetto.
 */
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

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNumCellulare() {
        return numCellulare;
    }

    public void setNumCellulare(String numCellulare) {
        this.numCellulare = numCellulare;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public LocalDate getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(LocalDate dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }
}