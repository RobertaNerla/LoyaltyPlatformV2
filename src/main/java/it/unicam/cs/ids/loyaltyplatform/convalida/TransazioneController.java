package it.unicam.cs.ids.loyaltyplatform.convalida;

import it.unicam.cs.ids.loyaltyplatform.dto.TransazioneDto;
import it.unicam.cs.ids.loyaltyplatform.exception.ResourceNotFoundException;
import it.unicam.cs.ids.loyaltyplatform.sottoscrizione.Sottoscrizione;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/transazioni")
public class TransazioneController {
    private final TransazioneService transazioneService;
    private final GestoreConvalida gestoreConvalida;

    @Autowired
    public TransazioneController(TransazioneService transazioneService, GestoreConvalida gestoreConvalida) {
        this.transazioneService = transazioneService;
        this.gestoreConvalida = gestoreConvalida;
    }

    //TODO: questa operazione può farla solo l'amministratore della piattaforma
    @GetMapping
    public List<Transazione> getAllTransactions() {
        return this.transazioneService.getTransazioni();
    }

    @GetMapping(path = "/azienda/{aziendaId}")
    public List<Transazione> getTransactionsByAzienda(@PathVariable Long aziendaId) {
        return transazioneService.getTransazioniByAzienda(aziendaId);
    }

    @GetMapping(path = "/cliente/{clienteId}")
    public List<Transazione> getTransactionByCliente(@PathVariable Long clienteId) {
        return transazioneService.getTransazioneByCliente(clienteId);
    }

    @PostMapping(path = "/{programmaId}")
    public ResponseEntity<Object> registraNuovaTransazione(@PathVariable Long programmaId
            , @RequestBody @Validated TransazioneDto dto) {
        try {
            Sottoscrizione updatedSottoscrizione = gestoreConvalida.convalidaTransazione(programmaId, dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(updatedSottoscrizione);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}