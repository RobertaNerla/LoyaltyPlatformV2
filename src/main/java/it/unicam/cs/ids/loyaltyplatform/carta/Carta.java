package it.unicam.cs.ids.loyaltyplatform.carta;

import it.unicam.cs.ids.loyaltyplatform.cliente.Cliente;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name = "Carta")
public class Carta {
    @Id
    @Column(name = "carta_id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartaId;
    @OneToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "cliente_id")
    private Cliente cliente;

    public Carta(Long cartaId, Cliente cliente) {
        this.cartaId = cartaId;
        this.cliente = cliente;
    }

    public Carta(Cliente cliente) {
        this.cliente = cliente;
    }

    public Carta() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carta carta = (Carta) o;
        return Objects.equals(getCartaId(), carta.getCartaId()) && Objects.equals(getCliente(), carta.getCliente());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCartaId(), getCliente());
    }
}
