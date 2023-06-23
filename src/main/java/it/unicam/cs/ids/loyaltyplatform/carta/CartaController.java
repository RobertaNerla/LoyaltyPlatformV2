package it.unicam.cs.ids.loyaltyplatform.carta;

import it.unicam.cs.ids.loyaltyplatform.dto.CartaDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/carte")
public class CartaController {
    private final CartaService cartaService;

    public CartaController(CartaService cartaService) {
        this.cartaService = cartaService;
    }

    @GetMapping
    public List<Carta> getAllCarte() {
        return cartaService.getCarte();
    }

    @PostMapping
    public ResponseEntity<Carta> registraNuovaCarta(@RequestBody CartaDto dto) {
        Carta newCarta = cartaService.addNewCarta(dto.getClienteId());
        return new ResponseEntity<>(newCarta, HttpStatus.CREATED);
    }
}
