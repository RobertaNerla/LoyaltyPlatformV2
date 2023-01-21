package it.unicam.cs.ids.loyaltyplatform.Service;

import it.unicam.cs.ids.loyaltyplatform.Entity.Cliente;
import it.unicam.cs.ids.loyaltyplatform.Repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> getClienti() {
        return clienteRepository.findAll();
    }

    public void addNewCliente(Cliente cliente) {
        Optional<Cliente> clientetByEmailOptional = clienteRepository.findClientetByEmail(cliente.getEmail());
        if (clientetByEmailOptional.isPresent()) {
            throw new IllegalStateException("Email gia' in uso");
        }

        Optional<Cliente> clienteByNumCellulareOptional = clienteRepository.findClienteByNumCellulare(cliente.getNumCellulare());
        if (clienteByNumCellulareOptional.isPresent()) {
            throw new IllegalStateException("Numero di cellulare gia' in uso");
        }

        clienteRepository.save(cliente);
    }

    public void deleteCliente(Long clienteId) {
        boolean exists = clienteRepository.existsById(clienteId);

        if (!exists) {
            throw new IllegalStateException("cliente con id " + clienteId + " non esiste!");
        }
        clienteRepository.deleteById(clienteId);
    }

    @Transactional
    public void updateClienteEmail(Long clienteId, String email) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new IllegalStateException("cliente con id " + clienteId + " non esiste!"));

        if (email != null && email.length() > 0 &&
                !Objects.equals(cliente.getEmail(), email)) {
            Optional<Cliente> clienteOptional = clienteRepository.findClientetByEmail(email);
            if (clienteOptional.isPresent()) {
                throw new IllegalStateException("email gia' in uso");
            }
            cliente.setEmail(email);
        }
    }
}
