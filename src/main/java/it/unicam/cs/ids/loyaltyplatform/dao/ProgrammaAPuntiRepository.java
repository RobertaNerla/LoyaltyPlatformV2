package it.unicam.cs.ids.loyaltyplatform.dao;

import it.unicam.cs.ids.loyaltyplatform.model.ProgrammaAPunti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProgrammaAPuntiRepository extends JpaRepository<ProgrammaAPunti, Long> {

    /**
     * Metodo corrispondente a una query che cerca un programma a punti di una data azienda
     *
     * @param aziendaId identificatore dell'azienda che offre il programma fedeltà che si sta cercando
     * @return se esiste il programma a punti dell'azienda che si sta cercando.
     */
    Optional<ProgrammaAPunti> findProgrammaAPuntiByAziendaId(Long aziendaId);

    /**
     * Metodo corrispondente a una query che cerca un programma a punti con un certo nome.
     *
     * @param nomeProgramma nome del programma che si sta cercando.
     * @return Il programma a punti con il nome dato.
     */
    Optional<ProgrammaAPunti> findProgrammaAPuntiByNomeProgramma(String nomeProgramma);
}
