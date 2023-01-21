package it.unicam.cs.ids.loyaltyplatform.Controller;

import it.unicam.cs.ids.loyaltyplatform.Entity.Azienda;
import it.unicam.cs.ids.loyaltyplatform.Entity.Cliente;
import it.unicam.cs.ids.loyaltyplatform.Service.AziendaService;
import it.unicam.cs.ids.loyaltyplatform.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
