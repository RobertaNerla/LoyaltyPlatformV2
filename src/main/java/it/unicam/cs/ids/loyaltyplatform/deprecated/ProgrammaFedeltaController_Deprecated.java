package it.unicam.cs.ids.loyaltyplatform.deprecated;

import it.unicam.cs.ids.loyaltyplatform.programmaFedelta.ProgrammaFedelta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/programmifedelta")
public class ProgrammaFedeltaController_Deprecated {
    public final ProgrammaFedeltaService_deprecated programmaFedeltaService;

    @Autowired
    public ProgrammaFedeltaController_Deprecated(ProgrammaFedeltaService_deprecated programmaFedeltaService) {
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
