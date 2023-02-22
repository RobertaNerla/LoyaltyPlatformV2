package it.unicam.cs.ids.loyaltyplatform.carta;

import it.unicam.cs.ids.loyaltyplatform.cliente.Cliente;
import it.unicam.cs.ids.loyaltyplatform.cliente.ClienteService;
import it.unicam.cs.ids.loyaltyplatform.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartaService {
    private final ClienteService clienteService;

    private final CartaRepository cartaRepository;
    @Autowired
    public CartaService(ClienteService clienteService, CartaRepository cartaRepository) {
        this.clienteService = clienteService;
        this.cartaRepository = cartaRepository;
    }

    public List<Carta> getCarte() {
        return cartaRepository.findAll();
    }

    public Carta addNewCarta(Long clienteId) throws ResourceNotFoundException {
        Cliente currentCliente = getClienteById(clienteId);
        Carta newCarta = new Carta(currentCliente);
        return cartaRepository.save(newCarta);
    }

    public Cliente getClienteByIdCarta(Long cartaId) throws ResourceNotFoundException{
        Carta carta = getCartaById(cartaId);
        return carta.getCliente();
    }

    private Carta getCartaById(Long cartaId) throws ResourceNotFoundException{
        Optional<Carta> optionalCarta = cartaRepository.findById(cartaId);
        if(optionalCarta.isEmpty()){
            throw new ResourceNotFoundException("Carta con id " + cartaId + " non trovata!");
        }else {
            return optionalCarta.get();
        }
    }



    private Cliente getClienteById(Long clienteId) {
       Optional<Cliente>  optionalCliente = clienteService.getClienteById(clienteId);
       if(optionalCliente.isEmpty()){
           throw  new ResourceNotFoundException("Cliente con id " + clienteId + " non esiste!");
       } else {
           return optionalCliente.get();
       }
    }
}
