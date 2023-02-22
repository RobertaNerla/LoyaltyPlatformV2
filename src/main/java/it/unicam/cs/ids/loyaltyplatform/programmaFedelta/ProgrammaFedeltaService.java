package it.unicam.cs.ids.loyaltyplatform.programmaFedelta;

import it.unicam.cs.ids.loyaltyplatform.azienda.Azienda;
import it.unicam.cs.ids.loyaltyplatform.azienda.AziendaService;
import it.unicam.cs.ids.loyaltyplatform.dto.ProgrammaFedeltaDto;
import it.unicam.cs.ids.loyaltyplatform.exception.ResourceAlreadyExistsException;
import it.unicam.cs.ids.loyaltyplatform.exception.ResourceNotFoundException;
import it.unicam.cs.ids.loyaltyplatform.sottoscrizione.Sottoscrizione;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgrammaFedeltaService {
    private final ProgramFactory programFactory;
    private final ProgrammaFedeltaRepository programmaFedeltaRepository;
    private final AziendaService aziendaService;

    @Autowired
    public ProgrammaFedeltaService(ProgramFactory programFactory,
                                   AziendaService aziendaService,
                                   ProgrammaFedeltaRepository programmaFedeltaRepository) {
        this.programFactory = programFactory;
        this.aziendaService = aziendaService;
        this.programmaFedeltaRepository = programmaFedeltaRepository;
    }

    public List<ProgrammaFedelta> getProgrammiFedelta() {
        return programmaFedeltaRepository.findAll();
    }

    public ProgrammaFedelta addNewProgrammaFedelta(ProgrammaFedeltaDto programmaDto, TipologiaProgramma tipo)
            throws ResourceNotFoundException {
        Optional<Azienda> aziendaOptional = aziendaService.getAziendaById(programmaDto.getAziendaId());
        if (aziendaOptional.isEmpty()) {
            throw new ResourceNotFoundException("Azienda con id " + programmaDto.getAziendaId() +
                    "non trovata!");
        }

        ProgrammaFedelta newProgram = programFactory.create(programmaDto, aziendaOptional.get(), tipo);
        aziendaService.addProgrammaToAzienda(newProgram.getAzienda().getAziendaId(), newProgram);
        return programmaFedeltaRepository.save(newProgram);
    }

    public Optional<ProgrammaFedelta> getProgrammaById(Long programId) {
        return programmaFedeltaRepository.findById(programId);
    }

    public void incrementaNumClienti(ProgrammaFedelta programma) {
        programma.setNumClienti(programma.getNumClienti() + 1);
        programmaFedeltaRepository.save(programma);
    }

    public void addNewSottoscrizione(ProgrammaFedelta programma, Sottoscrizione newSub) {
        Optional<ProgrammaFedelta> programmaOptional = programmaFedeltaRepository.findById(programma.getProgrammaId());
        if (programmaOptional.isEmpty()) {
            throw new ResourceNotFoundException("Programma fedeltà con l'id "
                    + programma.getProgrammaId() + " non trovato!");
        }

        boolean exists = programmaOptional.get().getSottoscrizioni().stream()
                .anyMatch(sottoscrizione -> sottoscrizione.getCliente()
                        .equals(newSub.getCliente()));
        if (exists) {
            throw new ResourceAlreadyExistsException("Il cliente " + newSub.getCliente()
                    + " è già iscritto!");
        }

        if (newSub.getProgramma().getProgrammaId() != programmaOptional.get().getProgrammaId()) {
            throw new IllegalArgumentException("La sottoscrizione non appartiene a questo programma, ma a " +
                    newSub.getProgramma());
        }
    }

    public void deleteProgrammaFedelta(Long programmaId) {
        Optional<ProgrammaFedelta> programmaOptional = programmaFedeltaRepository.findById(programmaId);
        if (programmaOptional.isEmpty()) {
            throw new ResourceNotFoundException("Un programma fedeltà con id " + programmaId +
                    "non esiste!");
        }

        programmaFedeltaRepository.deleteById(programmaId);
    }
}