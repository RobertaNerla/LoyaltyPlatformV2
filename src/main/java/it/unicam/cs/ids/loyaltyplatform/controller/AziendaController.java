package it.unicam.cs.ids.loyaltyplatform.controller;

import it.unicam.cs.ids.loyaltyplatform.model.Azienda;
import it.unicam.cs.ids.loyaltyplatform.service.AziendaService;
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

    @GetMapping
    public List<Azienda> getAziende() {
        return aziendaService.getAziende();
    }

    @PostMapping
    public void registraNuovaAzienda(@RequestBody Azienda azienda) {
        aziendaService.addNewAzienda(azienda);
    }

    @DeleteMapping(path = "{aziendaId}")
    public void deleteAzienda(@PathVariable("aziendaId") Long aziendaId) {
        aziendaService.deleteAzienda(aziendaId);
    }
}
