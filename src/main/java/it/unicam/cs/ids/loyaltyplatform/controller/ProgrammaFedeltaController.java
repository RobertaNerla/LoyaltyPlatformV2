package it.unicam.cs.ids.loyaltyplatform.controller;

import it.unicam.cs.ids.loyaltyplatform.model.ProgrammaAPunti;
import it.unicam.cs.ids.loyaltyplatform.model.ProgrammaFedelta;
import it.unicam.cs.ids.loyaltyplatform.service.ProgrammaAPuntiService;
import it.unicam.cs.ids.loyaltyplatform.service.ProgrammaFedeltaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/programmi")
public class ProgrammaFedeltaController {
    @Autowired
    private final ProgrammaFedeltaService programmaFedeltaService;

    @Autowired
    public ProgrammaFedeltaController(ProgrammaFedeltaService programmaFedeltaService) {
        this.programmaFedeltaService = programmaFedeltaService;
    }

    /**
     * Esegue una chiamata GET che restituisce una lista di tutti i programmi fedeltà a punti
     *
     * @return una lista di tutti i programmi fedeltà a punti
     */
    @GetMapping
    public List<ProgrammaFedelta> getProgrammiAPunti() {
        return this.programmaFedeltaService.getProgrammi();
    }
}
