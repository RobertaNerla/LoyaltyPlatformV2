package it.unicam.cs.ids.loyaltyplatform.sottoscrizione;

import it.unicam.cs.ids.loyaltyplatform.dto.SottoscrizioneDto;
import it.unicam.cs.ids.loyaltyplatform.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/sottoscrizioni")
public class SottoscrizioneController {
    private final SottoscrizioneService sottoscrizioneService;

    @Autowired
    public SottoscrizioneController(SottoscrizioneService sottoscrizioneService) {
        this.sottoscrizioneService = sottoscrizioneService;
    }

    @GetMapping
    public List<Sottoscrizione> getSottoscrizioni() {
        return sottoscrizioneService.getSottoscrizioni();
    }

    @PostMapping
    public ResponseEntity<Sottoscrizione> registraNuovaSottoscrizione(@RequestBody @Validated SottoscrizioneDto dto) {
        try {
            Sottoscrizione newSub = sottoscrizioneService.addNewSottoscrizione(dto.getClienteId(), dto.getProgrammaId());
            return new ResponseEntity<>(newSub,HttpStatus.CREATED);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}