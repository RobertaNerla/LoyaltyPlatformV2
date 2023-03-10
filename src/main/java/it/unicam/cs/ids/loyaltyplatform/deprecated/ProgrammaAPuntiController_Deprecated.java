package it.unicam.cs.ids.loyaltyplatform.deprecated;

import it.unicam.cs.ids.loyaltyplatform.dto.ProgrammaAPuntiDTO;
import it.unicam.cs.ids.loyaltyplatform.dto.ProgrammaFedeltaDto;
import it.unicam.cs.ids.loyaltyplatform.azienda.Azienda;
import it.unicam.cs.ids.loyaltyplatform.programmaFedelta.ProgrammaAPunti;
import it.unicam.cs.ids.loyaltyplatform.programmaFedelta.ProgrammaFedelta;
import it.unicam.cs.ids.loyaltyplatform.azienda.AziendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/progPunti")
public class ProgrammaAPuntiController_Deprecated {
    public final ProgrammaAPuntiService_deprecated progAPuntiService;

    private final AziendaService aziendaService;

    @Autowired
    public ProgrammaAPuntiController_Deprecated(ProgrammaAPuntiService_deprecated progAPuntiService, AziendaService aziendaService) {
        this.progAPuntiService = progAPuntiService;
        this.aziendaService = aziendaService;
    }

    /**
     * Esegue una chiamata GET che restituisce una lista di tutti i programmi fedeltà a punti
     *
     * @return una lista di tutti i programmi fedeltà a punti
     */
    @GetMapping
    public List<ProgrammaAPunti> getProgrammiAPunti() {
        return this.progAPuntiService.getProgrammiAPunti();
    }

    @PostMapping
    public ResponseEntity<ProgrammaAPuntiDTO> registraNuovoProgrammaAPunti(@RequestBody @Validated ProgrammaFedeltaDto dto) {
        Optional<Azienda> azienda = aziendaService.getAziendaById(dto.getAziendaId());
        if(azienda.isPresent()){
            ProgrammaFedelta programmaAPunti = progAPuntiService.addNewProgrammaAPunti(dto,azienda.get());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping(path = "{programmaId}")
    public void deleteProgrammaAPunti(@PathVariable("programmaId") Long programmaId) {
        this.progAPuntiService.deleteProgrammaAPunti(programmaId);
    }
}
