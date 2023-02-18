package it.unicam.cs.ids.loyaltyplatform.cliente;

import it.unicam.cs.ids.loyaltyplatform.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    /**
     * Metodo corrispondente ad una query che cerca un cliente con la corrispondente email
     *
     * @param email l'email del cliente che si desidera trovare
     * @return un Optional<Cliente> che corrisponde al cliente con la specifica email
     */
    Optional<Cliente> findClienteByEmail(String email);

    /**
     * Metodo corrispondente ad una query che cerca un cliente con il corrispondente numero di cellulare
     *
     * @param numCellulare il numero di cellulare del cliente ci si desidera trovare
     * @return Optional<Cliente> che corrisponde al cliente con il specifico numero di cellulare
     */
    Optional<Cliente> findClienteByNumCellulare(String numCellulare);
}
