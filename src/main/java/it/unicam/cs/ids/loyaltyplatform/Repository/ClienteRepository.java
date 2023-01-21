package it.unicam.cs.ids.loyaltyplatform.Repository;

import it.unicam.cs.ids.loyaltyplatform.Entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findClientetByEmail(String email);

    Optional<Cliente> findClienteByNumCellulare(String numCellulare);
}
