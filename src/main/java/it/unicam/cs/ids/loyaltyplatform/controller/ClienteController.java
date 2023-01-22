package it.unicam.cs.ids.loyaltyplatform.controller;

import it.unicam.cs.ids.loyaltyplatform.model.Cliente;
import it.unicam.cs.ids.loyaltyplatform.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/cliente")
public class ClienteController {
    public final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> getClienti() {
        return clienteService.getClienti();
    }

    @PostMapping
    public void registraNuovoCliente(@RequestBody Cliente cliente) {
        clienteService.addNewCliente(cliente);
    }

    @DeleteMapping(path = "{clienteId}")
    public void deleteCliente(@PathVariable("clienteId") Long clienteId) {
        clienteService.deleteCliente(clienteId);
    }

    @PutMapping(path = "{clienteId}")
    public void updateClienteEmail(@PathVariable("clienteId") Long clienteId, String email) {
        clienteService.updateClienteEmail(clienteId, email);
    }
}
