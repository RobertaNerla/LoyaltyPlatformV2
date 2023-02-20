package it.unicam.cs.ids.loyaltyplatform.programmaFedelta;

import it.unicam.cs.ids.loyaltyplatform.azienda.Azienda;
import it.unicam.cs.ids.loyaltyplatform.azienda.AziendaService;
import it.unicam.cs.ids.loyaltyplatform.dto.ProgrammaFedeltaDto;
import it.unicam.cs.ids.loyaltyplatform.exception.ResourceNotFoundException;
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
}
