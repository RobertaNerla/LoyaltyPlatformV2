package it.unicam.cs.ids.loyaltyplatform.programmaFedelta;

import it.unicam.cs.ids.loyaltyplatform.dao.ProgrammaFedeltaRepository;
import it.unicam.cs.ids.loyaltyplatform.dto.ProgrammaFedeltaDto;
import it.unicam.cs.ids.loyaltyplatform.model.Azienda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Service
public class ProgrammaFedeltaServicee {

    private final ProgramFactory programFactory;
    private final ProgrammaFedeltaRepository programmaFedeltaRepository;
    @Autowired
    public ProgrammaFedeltaServicee(ProgramFactory programFactory, ProgrammaFedeltaRepository programmaFedeltaRepository) {
        this.programFactory = programFactory;
        this.programmaFedeltaRepository = programmaFedeltaRepository;
    }

    @GetMapping
    public List<ProgrammaFedelta> getProgrammiFedelta(){
        return programmaFedeltaRepository.findAll();
    }

    @PostMapping
    public ProgrammaFedelta addNewProgrammaFedelta(@Validated ProgrammaFedeltaDto programma, Azienda azienda,TipologiaProgramma tipo){
        ProgrammaFedelta newProgram = programFactory.create(programma, azienda, tipo);
        return programmaFedeltaRepository.save(newProgram);
    }

    public Optional<ProgrammaFedelta> getProgrammaById(Long programId){
        return programmaFedeltaRepository.findById(programId);
    }
}
