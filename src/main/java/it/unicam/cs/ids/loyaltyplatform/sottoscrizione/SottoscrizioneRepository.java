package it.unicam.cs.ids.loyaltyplatform.sottoscrizione;

import it.unicam.cs.ids.loyaltyplatform.cliente.Cliente;
import it.unicam.cs.ids.loyaltyplatform.programmaFedelta.ProgrammaFedelta;
import it.unicam.cs.ids.loyaltyplatform.sottoscrizione.Sottoscrizione;
import it.unicam.cs.ids.loyaltyplatform.sottoscrizione.SottoscrizioneProgrammaAPunti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SottoscrizioneRepository extends JpaRepository<Sottoscrizione, Long> {
    Optional<Sottoscrizione> findSottoscrizioneByClienteAndProgramma(Cliente cliente, ProgrammaFedelta programma);

}
