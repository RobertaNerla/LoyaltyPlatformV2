package it.unicam.cs.ids.loyaltyplatform.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe che rappresenta un'azienda nel sistema.
 * Corrisponde anche all'omonima entità nel database del progetto.
 */

@Getter
@Setter
@ToString
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

    @Column(name = "p_iva", nullable = false, columnDefinition = "TEXT")
    String pIva;

    @OneToMany(mappedBy = "azienda", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<ProgrammaFedelta> programmiFedelta;

    /**
     * Costruttore di default dell'azienda
     */
    public Azienda() {
        programmiFedelta = new ArrayList<>();
    }

    /**
     * @param aziendaId id dell'azienda
     * @param nome      nome dell'azienda
     * @param indirizzo indirizzo dell'azienda
     * @param pIva      partita iva dell'azienda
     */
    public Azienda(Long aziendaId, String nome, String indirizzo, String pIva) {
        this.aziendaId = aziendaId;
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.pIva = pIva;
        programmiFedelta = new ArrayList<>();
    }

    /**
     * Costruttore senza id, perchè esso viene generato automaticamente.
     *
     * @param nome      nome dell'azienda
     * @param indirizzo indirizzo dell'azienda
     * @param pIva      partita iva dell'azienda
     */
    public Azienda(String nome, String indirizzo, String pIva) {
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.pIva = pIva;
        programmiFedelta = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Azienda azienda = (Azienda) o;
        return aziendaId != null && Objects.equals(aziendaId, azienda.aziendaId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
