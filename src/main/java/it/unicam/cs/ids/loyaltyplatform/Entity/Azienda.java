package it.unicam.cs.ids.loyaltyplatform.Entity;

import jakarta.persistence.*;

@Entity(name = "Azienda")
@Table(name = "azienda")
public class Azienda {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "azienda_id", nullable = false, updatable = false)
    Long aziendaId;

    @Column(name = "nome", nullable = false, columnDefinition = "TEXT")
    String nome;

    @Column(name = "indirizzo", nullable = false, columnDefinition = "TEXT")
    String indirizzo;

    public Azienda() {
    }

    public Azienda(Long aziendaId, String nome, String indirizzo) {
        this.aziendaId = aziendaId;
        this.nome = nome;
        this.indirizzo = indirizzo;
    }

    public Azienda(String nome, String indirizzo) {
        this.nome = nome;
        this.indirizzo = indirizzo;
    }

    public Long getAziendaId() {
        return aziendaId;
    }

    public void setAziendaId(Long aziendaId) {
        this.aziendaId = aziendaId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }
}
