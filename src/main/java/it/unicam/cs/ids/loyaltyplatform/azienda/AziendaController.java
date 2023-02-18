package it.unicam.cs.ids.loyaltyplatform.azienda;

import it.unicam.cs.ids.loyaltyplatform.azienda.Azienda;
import it.unicam.cs.ids.loyaltyplatform.azienda.AziendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Classe controller, che gestisce le chiamate CRUD dell'entità azienda
 */
@RestController
@RequestMapping(path = "/api/azienda")
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

    /**
     * Esegue una chiamata POST che inserisce una azienda nel database
     *
     * @param azienda da inserire nel databse
     */
    @PostMapping(consumes = "application/json", produces = "application/json")
    public void registraNuovaAzienda(@RequestBody Azienda azienda) {
        aziendaService.addNewAzienda(azienda);
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