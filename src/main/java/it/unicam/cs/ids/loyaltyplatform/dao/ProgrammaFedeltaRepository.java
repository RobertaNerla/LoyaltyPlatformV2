package it.unicam.cs.ids.loyaltyplatform.dao;

import it.unicam.cs.ids.loyaltyplatform.model.Azienda;
import it.unicam.cs.ids.loyaltyplatform.model.ProgrammaFedelta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProgrammaFedeltaRepository extends JpaRepository<ProgrammaFedelta, Long> {
    /**
     * Metodo corrispondente a una query che cerca un programma a punti di una data azienda
     *
     * @param azienda identificatore dell'azienda che offre il programma fedeltà che si sta cercando
     * @return se esiste il programma a punti dell'azienda che si sta cercando.
     */
    Optional<ProgrammaFedelta> findProgrammaFedeltaByAzienda(Azienda azienda);

    /**
     * Metodo corrispondente a una query che cerca un programma a punti con un certo nome.
     *
     * @param nomeProgramma nome del programma che si sta cercando.
     * @return Il programma a punti con il nome dato.
     */
    Optional<ProgrammaFedelta> findProgrammaAPuntiByNomeProgramma(String nomeProgramma);
}
