package it.unicam.cs.ids.loyaltyplatform.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity(name = "Cliente")
@Table(name = "cliente",
        uniqueConstraints = {
                @UniqueConstraint(name = "student_email_unique",
                        columnNames = "email"),
                @UniqueConstraint(name = "student_num_cellulare_unique",
                        columnNames = "num_cellulare")
        })
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "clienteId",
            nullable = false,
            updatable = false)
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
    public String email;

    @Column(name = "indirizzo",
            nullable = false,
            columnDefinition = "TEXT")
    private String indirizzo;

    @Column(name = "data_nascita")
    private Date dataDiNascita;

    @Column(name = "codice_fiscale",
            nullable = false,
            columnDefinition = "TEXT")
    private String codiceFiscale;

    public Cliente() {
    }

    public Cliente(Long clienteId, String nome, String cognome, String numCellulare, String indirizzo, Date dataDiNascita, String codiceFiscale) {
        this.clienteId = clienteId;
        this.nome = nome;
        this.cognome = cognome;
        this.numCellulare = numCellulare;
        this.indirizzo = indirizzo;
        this.dataDiNascita = dataDiNascita;
        this.codiceFiscale = codiceFiscale;
    }

    public Cliente(String nome, String cognome, String numCellulare, String indirizzo, Date dataDiNascita, String codiceFiscale) {
        this.nome = nome;
        this.cognome = cognome;
        this.numCellulare = numCellulare;
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

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public Date getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(Date dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }
}