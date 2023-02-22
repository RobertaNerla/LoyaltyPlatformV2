package it.unicam.cs.ids.loyaltyplatform.azienda;

import it.unicam.cs.ids.loyaltyplatform.programmaFedelta.ProgrammaFedelta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Classe controller, che gestisce le chiamate CRUD dell'entità azienda
 */
@RestController
@RequestMapping(path = "/api/aziende")
public class AziendaController {
    public final AziendaService aziendaService;

    @Autowired
    public AziendaController(AziendaService aziendaService) {
        this.aziendaService = aziendaService;
    }

    /**
     * Esegue una chiamata GET che restituisce una lista con tutte le aziende
     *
     * @return una List<Aziende> che contiene tutte le aziende del database
     */
    @GetMapping
    public List<Azienda> getAziende() {
        return aziendaService.getAziende();
    }

    @GetMapping(path = "/programmi/{aziendaId}")
    public List<ProgrammaFedelta> getProgrammiAzienda(@PathVariable("aziendaId") Long aziendaId) {
        return aziendaService.getProgrammiAzienda(aziendaId);
    }

    /**
     * Esegue una chiamata POST che inserisce una azienda nel database
     *
     * @param azienda da inserire nel databse
     */
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Azienda> registraNuovaAzienda(@RequestBody Azienda azienda) {
        Azienda newAzienda = aziendaService.addNewAzienda(azienda);
        return new ResponseEntity<>(newAzienda, HttpStatus.CREATED);
    }

    /**
     * Esegue una chiamata DELETE che elimina un'azienda con lo specifico id dal database
     *
     * @param aziendaId l'id dell'azienda che si desidera eliminare
     */
    @DeleteMapping(path = "{aziendaId}")
    public void deleteAzienda(@PathVariable("aziendaId") Long aziendaId) {
        aziendaService.deleteAzienda(aziendaId);
    }
}