package it.unicam.cs.ids.loyaltyplatform.controller;

import it.unicam.cs.ids.loyaltyplatform.dto.SottoscrizioneProgrammaAPuntiDTO;
import it.unicam.cs.ids.loyaltyplatform.model.ProgrammaAPunti;
import it.unicam.cs.ids.loyaltyplatform.service.ProgrammaAPuntiService;
import it.unicam.cs.ids.loyaltyplatform.service.ProgrammaAPuntiTrackerService;
import it.unicam.cs.ids.loyaltyplatform.tracker.ProgrammaAPuntiTracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/sottoscrizioni/ProgrammaAPunti")
public class SottoscrizioneProgrammaAPuntiController {

    public ProgrammaAPuntiService programmaAPuntiService;
    public ProgrammaAPuntiTrackerService programmaAPuntiTrackerService;

    @Autowired
    public SottoscrizioneProgrammaAPuntiController(ProgrammaAPuntiService programmaAPuntiService,
                                                   ProgrammaAPuntiTrackerService programmaAPuntiTrackerService) {
        this.programmaAPuntiService = programmaAPuntiService;
        this.programmaAPuntiTrackerService = programmaAPuntiTrackerService;
    }

    @PostMapping
    public ResponseEntity<ProgrammaAPuntiTracker> sottoscriviProgrammaAPunti(@RequestBody SottoscrizioneProgrammaAPuntiDTO dto) {
        ProgrammaAPunti programmaAPunti = programmaAPuntiService.getProgramById(dto.getProgrammaId());
        if (programmaAPunti == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        programmaAPuntiService.aggiornaNumClienti(programmaAPunti.getProgramId());
        ProgrammaAPuntiTracker programmaAPuntiTracker = ProgrammaAPuntiTrackerService.addNewProgrammaAPuntiTracker(
                dto.getClienteId(), programmaAPunti.getProgramId());

        return new ResponseEntity<>.(programmaAPuntiTracker, HttpStatus.CREATED);
    }
}
