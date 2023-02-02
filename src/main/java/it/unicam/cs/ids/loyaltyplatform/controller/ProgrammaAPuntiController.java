package it.unicam.cs.ids.loyaltyplatform.controller;

import it.unicam.cs.ids.loyaltyplatform.dto.ProgrammaAPuntiDto;
import it.unicam.cs.ids.loyaltyplatform.model.ProgrammaAPunti;
import it.unicam.cs.ids.loyaltyplatform.service.ProgrammaAPuntiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/progPunti")
public class ProgrammaAPuntiController {
    @Autowired
    private final ProgrammaAPuntiService progAPuntiService;

    @Autowired
    public ProgrammaAPuntiController(ProgrammaAPuntiService progAPuntiService) {
        this.progAPuntiService = progAPuntiService;
    }

    /**
     * Esegue una chiamata GET che restituisce una lista di tutti i programmi fedeltà a punti
     *
     * @return una lista di tutti i programmi fedeltà a punti
     */
    @GetMapping
    public List<ProgrammaAPunti> getProgrammiAPunti() {
        return this.progAPuntiService.getProgrammaAPunti();
    }

    @PostMapping
    public ResponseEntity<ProgrammaAPuntiDto> registraNuovoProgrammaAPunti(@RequestBody @Validated ProgrammaAPuntiDto dto) {
        ProgrammaAPunti programmaAPunti = progAPuntiService.addNewProgrammaAPunti(dto);
        return new ResponseEntity<>(new ProgrammaAPuntiDto(programmaAPunti), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{programmaId}")
    public void deleteProgrammaAPunti(@PathVariable("programmaId") Long programmaId) {
        this.progAPuntiService.deleteProgrammaAPunti(programmaId);
    }
}
