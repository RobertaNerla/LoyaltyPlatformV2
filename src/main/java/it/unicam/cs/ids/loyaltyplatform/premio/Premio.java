package it.unicam.cs.ids.loyaltyplatform.premio;

import com.fasterxml.jackson.annotation.JsonBackReference;
import it.unicam.cs.ids.loyaltyplatform.programmaFedelta.ProgrammaAPunti;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CollectionId;
@Getter
@Setter
@ToString
@Entity(name = "Premio")
@Table(name = "premio")
public class Premio {
    @Id
    @Column(name = "premio_id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long premioId;
    @Column(name = "nome")
    private String nome;
    @Column(name = "descrizione")
    private String descrizione;
    @Column(name ="costo_punti")
    private int costoPunti;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "programma_a_punti_id")
    private ProgrammaAPunti programmaAPunti;

    public Premio() {
    }

    public Premio(String nome, String descrizione, int costoPunti, ProgrammaAPunti programmaAPunti) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.costoPunti = costoPunti;
        this.programmaAPunti = programmaAPunti;
    }

    public Premio(Long premioId, String nome,String descrizione, int costoPunti, ProgrammaAPunti programmaAPunti) {
        this.nome = nome;
        this.premioId = premioId;
        this.descrizione = descrizione;
        this.costoPunti = costoPunti;
        this.programmaAPunti = programmaAPunti;
    }
}

