package it.unicam.cs.ids.loyaltyplatform.Repository;

import it.unicam.cs.ids.loyaltyplatform.Entity.Azienda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AziendaRepository extends JpaRepository<Azienda, Long> {

    Optional<Azienda> findByNomeAndIndirizzo(String nome, String indirizzo);
}