package it.unicam.cs.ids.loyaltyplatform.controller;

import it.unicam.cs.ids.loyaltyplatform.dto.ProgrammaAPuntiDto;
import it.unicam.cs.ids.loyaltyplatform.model.ProgrammaAPunti;
import it.unicam.cs.ids.loyaltyplatform.service.ProgrammaAPuntiService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<ProgrammaAPunti> getProgrammiAPunti(){
        return this.progAPuntiService.getProgAPunti();
    }

    @PostMapping
    public void registraNuovoProgrammaAPunti(@RequestBody ProgrammaAPuntiDto dto){
        if(!isValidInput(dto.getAziendaId(), dto.getPointsEur())){
            throw new IllegalArgumentException("I campi inseriti non sono validi");
        } else {
            this.progAPuntiService.addNewProgrammaAPunti(dto);
        }
    }

    @DeleteMapping(path = "{programmaId}")
    public void deleteProgrammaAPunti(@PathVariable("programmaId") Long programmaId){
        this.progAPuntiService.deleteProgrammaAPunti(programmaId);
    }

    private boolean isValidInput(Long aziendaId, double pointsEur){
        return false;
    }

    private boolean isValidAziendaId(Long AziendaId){
        return false;
    }

    private boolean isValidPointsEur(double pointsEur){
        return false;
    }
}
