package it.unicam.cs.ids.loyaltyplatform.controller;

import it.unicam.cs.ids.loyaltyplatform.dto.SottoscrizioneProgrammaAPuntiDTO;
import it.unicam.cs.ids.loyaltyplatform.exception.ResourceNotFoundException;
import it.unicam.cs.ids.loyaltyplatform.service.ProgrammaAPuntiTrackerService;
import it.unicam.cs.ids.loyaltyplatform.tracker.ProgrammaAPuntiTracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/sottoscrizioni/ProgrammaAPunti")
public class SottoscrizioneProgrammaAPuntiController {
    public final ProgrammaAPuntiTrackerService programmaAPuntiTrackerService;

    @Autowired
    public SottoscrizioneProgrammaAPuntiController(ProgrammaAPuntiTrackerService programmaAPuntiTrackerService) {
        this.programmaAPuntiTrackerService = programmaAPuntiTrackerService;
    }

    /**
     * Sottoscrive un cliente a un programma fedeltà, creando un apposito tracker
     *
     * @param dto data transfer object che contiene il cliente e il programma che egli sottoscrive
     * @return restituisce l'esito dell'operazione sotto forma di una response entity
     */
    @PostMapping
    public ResponseEntity<ProgrammaAPuntiTracker> sottoscriviProgrammaAPunti(
            @RequestBody @Validated SottoscrizioneProgrammaAPuntiDTO dto) {
        try {
            ProgrammaAPuntiTracker programmaAPuntiTracker = programmaAPuntiTrackerService.addNewProgrammaAPuntiTracker(dto.getCliente(), dto.getProgramma());
            return new ResponseEntity<>(programmaAPuntiTracker, HttpStatus.CREATED);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}