package it.unicam.cs.ids.loyaltyplatform.convalida;

import it.unicam.cs.ids.loyaltyplatform.azienda.AziendaService;
import it.unicam.cs.ids.loyaltyplatform.cliente.ClienteService;
import it.unicam.cs.ids.loyaltyplatform.dto.TransazioneDto;
import it.unicam.cs.ids.loyaltyplatform.exception.ResourceNotFoundException;
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

    @Autowired
    public TransazioneController(TransazioneService transazioneService) {
        this.transazioneService = transazioneService;
    }
    //TODO: questa operazione può farla solo l'amministratore della piattaforma
    @GetMapping
    public List<Transazione> getAllTransactions(){
        return this.transazioneService.getTransazioni();
    }

    @GetMapping (path = "/azienda/{aziendaId}")
    public List<Transazione> getTransactionsByAzienda(@PathVariable Long aziendaId){
        //TODO: completare
        return null;
    }

    @GetMapping (path = "/cliente/{clienteId}")
    public List<Transazione> getTransactionByCliente(@PathVariable Long clienteId){
        //TODO: completare
        return null;
    }

    @PostMapping
    public ResponseEntity<Transazione> registraNuovaTransazione(@RequestBody @Validated TransazioneDto dto){
        try{
            Transazione transazione = transazioneService.addNewTransazione(dto);
            return new ResponseEntity<>(transazione, HttpStatus.CREATED);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
