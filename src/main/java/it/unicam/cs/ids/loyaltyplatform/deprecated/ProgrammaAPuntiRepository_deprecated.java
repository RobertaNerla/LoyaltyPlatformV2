package it.unicam.cs.ids.loyaltyplatform.deprecated;

import it.unicam.cs.ids.loyaltyplatform.azienda.Azienda;
import it.unicam.cs.ids.loyaltyplatform.programmaFedelta.ProgrammaAPunti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProgrammaAPuntiRepository_deprecated extends JpaRepository<ProgrammaAPunti, Long> {

    /**
     * Metodo corrispondente a una query che cerca un programma a punti di una data azienda
     *
     * @param azienda identificatore dell'azienda che offre il programma fedeltà che si sta cercando
     * @return se esiste il programma a punti dell'azienda che si sta cercando.
     */
    Optional<ProgrammaAPunti> findProgrammaAPuntiByAzienda(Azienda azienda);

    /**
     * Metodo corrispondente a una query che cerca un programma a punti con un certo nome.
     *
     * @param nomeProgramma nome del programma che si sta cercando.
     * @return Il programma a punti con il nome dato.
     */
    Optional<ProgrammaAPunti> findProgrammaAPuntiByNomeProgramma(String nomeProgramma);
}
