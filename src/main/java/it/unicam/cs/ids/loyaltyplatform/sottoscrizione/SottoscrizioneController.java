package it.unicam.cs.ids.loyaltyplatform.sottoscrizione;

import it.unicam.cs.ids.loyaltyplatform.dto.SottoscrizioneDto;
import it.unicam.cs.ids.loyaltyplatform.model.Azienda;
import it.unicam.cs.ids.loyaltyplatform.model.Cliente;
import it.unicam.cs.ids.loyaltyplatform.programmaFedelta.ProgrammaFedelta;
import it.unicam.cs.ids.loyaltyplatform.programmaFedelta.ProgrammaFedeltaServicee;
import it.unicam.cs.ids.loyaltyplatform.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping (path = "api/sottoscrizioni")
public class SottoscrizioneController {

    private final SottoscrizioneService sottoscrizioneService;
    private final ClienteService clienteService;
    private final ProgrammaFedeltaServicee programmaFedeltaService;

    @Autowired
    public SottoscrizioneController(SottoscrizioneService sottoscrizioneService, ClienteService clienteService, ProgrammaFedeltaServicee programmaFedeltaService) {
        this.sottoscrizioneService = sottoscrizioneService;
        this.clienteService = clienteService;
        this.programmaFedeltaService = programmaFedeltaService;
    }

    @GetMapping
    public List<Sottoscrizione> getSottoscrizioni(){
        return sottoscrizioneService.getSottoscrizioni();
    }

    @PostMapping
    public ResponseEntity<Sottoscrizione> registraNuovaSottoscrizione(SottoscrizioneDto dto){
        Optional<Cliente> cliente = clienteService.getClienteById(dto.getClienteId());
        Optional<ProgrammaFedelta> programma = programmaFedeltaService.getProgrammaById(dto.getProgrammaId());
        if(cliente.isPresent() && programma.isPresent()){
            Sottoscrizione newSub = sottoscrizioneService.addNewSottoscrizione(cliente.get(), programma.get());
            //TODO: Aggiungere la sottoscrizione su cliente e programma
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

}
