package it.unicam.cs.ids.loyaltyplatform.programmaFedelta;

import it.unicam.cs.ids.loyaltyplatform.dto.PremioDto;
import it.unicam.cs.ids.loyaltyplatform.dto.ProgrammaFedeltaDto;
import it.unicam.cs.ids.loyaltyplatform.exception.ResourceNotFoundException;
import it.unicam.cs.ids.loyaltyplatform.premio.Premio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/programmi")
public class ProgrammaFedeltaController {
    private final List<Template> availableTemplates;
    private final ProgrammaFedeltaService programmaFedeltaService;

    @Autowired
    public ProgrammaFedeltaController(ProgrammaFedeltaService programmaFedeltaService) {
        this.programmaFedeltaService = programmaFedeltaService;
        this.availableTemplates = new ArrayList<>(List.of(
                new Template(TipologiaProgramma.punti),
                new Template(TipologiaProgramma.cashback),
                new Template(TipologiaProgramma.livelli),
                new Template(TipologiaProgramma.membership),
                new Template(TipologiaProgramma.coalizione)));
    }

    @GetMapping(path = "/templates")
    public ResponseEntity<List<Template>> getAvailableTemplates() {
        return new ResponseEntity<>(availableTemplates, HttpStatus.OK);
    }

    @GetMapping
    public List<ProgrammaFedelta> getProgrammiFedelta() {
        return this.programmaFedeltaService.getProgrammiFedelta();
    }

    /**
     * Questo metodo serve per poter aggiungere un programma fedelta.
     *
     * @param tipo Specifica il tipo del programma fedelta che si vuole aggiungere.
     *             L'utente al click sul template scelto per il suo nuovo programma fedeltà
     *             viene indirizzato su questo endpoint.
     *             Se l'utente sceglierà il template per il programma a punti allora il path su cui effettuerà
     *             la richiesta sarà: api/programmi/nuovoProgramma/punti
     * @param dto  dati inseriti nel Body della richiesta.
     * @return Risposta Http.
     * <p>
     * COME TESTARLO:
     * Qui sotto è riportato un esempio di Body per la richiesta POST dell'aggiunta di un programma.
     * Ovviamente l'id dell'azienda va modificato in base a quello presente nel DB.
     * Per evitare errori, eseguire la GET sopra per scovare un aziendaId esistente.
     * <p>
     * {
     * "tipo" : "punti",
     * "aziendaId":"652",
     * "name":"prova8",
     * "pointsEur" : "4"
     * }
     */
    @PostMapping(path = "/nuovoProgramma/{tipo_programma}")
    public ResponseEntity<Object> registraNuovoProgrammaFedelta(
            @PathVariable("tipo_programma") TipologiaProgramma tipo, @RequestBody @Validated ProgrammaFedeltaDto dto) {
        try {
            ProgrammaFedelta programma = programmaFedeltaService.addNewProgrammaFedelta(dto, tipo);
            return new ResponseEntity<>(programma, HttpStatus.CREATED);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "{programmaId}")
    public void deleteProgrammaFedelta(@PathVariable("programmaId") Long programmaId) {
        programmaFedeltaService.deleteProgrammaFedelta(programmaId);
    }

    @PostMapping(path = "punti/{programmaId}/premi")
    public ResponseEntity<List<Premio>> aggiungiPremioAlCatalogo(@PathVariable("programmaId") Long programmaId, @RequestBody PremioDto dto) {
        try {
            List<Premio> updatedCatalogo = programmaFedeltaService.aggiungiPremio(programmaId, dto);
            return new ResponseEntity<>(updatedCatalogo, HttpStatus.CREATED);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}