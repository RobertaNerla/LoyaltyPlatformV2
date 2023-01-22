package it.unicam.cs.ids.loyaltyplatform.dao;

import it.unicam.cs.ids.loyaltyplatform.model.Azienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AziendaRepository extends JpaRepository<Azienda, Long> {

    Optional<Azienda> findByNomeAndIndirizzo(String nome, String indirizzo);
}