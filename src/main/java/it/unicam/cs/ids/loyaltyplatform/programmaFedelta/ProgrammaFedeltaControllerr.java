package it.unicam.cs.ids.loyaltyplatform.programmaFedelta;

import it.unicam.cs.ids.loyaltyplatform.dto.ProgrammaFedeltaDto;
import it.unicam.cs.ids.loyaltyplatform.model.Azienda;
import it.unicam.cs.ids.loyaltyplatform.service.AziendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping (path = "api/programma")
public class ProgrammaFedeltaControllerr {

    private final List<Template> availableTemplates;
    private final ProgrammaFedeltaServicee programmaFedeltaService;

    private final AziendaService aziendaService;


    @Autowired
    public ProgrammaFedeltaControllerr(ProgrammaFedeltaServicee programmaFedeltaService, AziendaService aziendaService) {
        this.programmaFedeltaService = programmaFedeltaService;
        this.aziendaService = aziendaService;
        this.availableTemplates = new ArrayList<>(List.of(
                new Template(TipologiaProgramma.punti),
                new Template(TipologiaProgramma.cashback),
                new Template(TipologiaProgramma.livelli),
                new Template(TipologiaProgramma.membership),
                new Template(TipologiaProgramma.coalizione)));
    }
    @GetMapping(path = "/templates")
    public ResponseEntity<List<Template>> getAvailableTemplates() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(availableTemplates, HttpStatus.OK);
    }

    @GetMapping
    public List<ProgrammaFedelta> getProgrammiFedelta(){
        return this.programmaFedeltaService.getProgrammiFedelta();
    }

    /**
     *
     * Questo metodo serve per poter aggiungere un programma fedelta.
     *
     * @param tipo Specifica il tipo del programma fedelta che si vuole aggiungere.
     *             L'utente al click sul template scelto per il suo nuovo programma fedeltà
     *             viene indirizzato su questo endpoint.
     *             Se l'utente sceglierà il template per il programma a punti allora il path su cui effettuerà
     *             la richiesta sarà: api/programma/new/punti
     * @param dto dati inseriti nel Body della richiesta.
     * @return Risposta Http.
     *
     * COME TESTARLO:
     * Qui sotto è riportato un esempio di Body per la richiesta POST dell'aggiunta di un programma.
     * Ovviamente l'id dell'azienda va modificato in base a quello presente nel DB.
     * Per evitare errori, eseguire la GET sopra per scovare un aziendaId esistente.
     *
     *  {
     *     "tipo" : "punti",
     *     "aziendaId":"652",
     *     "name":"prova8",
     *     "pointsEur" : "4"
     *  }
     */
    @PostMapping(path ="/new/{tipo_programma}")
    public ResponseEntity<ProgrammaFedelta> registraNuovoProgrammaFedelta(@PathVariable("tipo_programma") TipologiaProgramma tipo,  @RequestBody @Validated ProgrammaFedeltaDto dto){
        Optional<Azienda> azienda = aziendaService.getAziendaById(dto.getAziendaId());
        if(azienda.isPresent()){
            ProgrammaFedelta programma = programmaFedeltaService.addNewProgrammaFedelta(dto,azienda.get(), tipo);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}





