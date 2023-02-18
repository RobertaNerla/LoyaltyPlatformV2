package it.unicam.cs.ids.loyaltyplatform.cliente;

import it.unicam.cs.ids.loyaltyplatform.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Classe che racchiude la business logic legata all'entità cliente.
 */
@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    /**
     * Restituisce tutti i clienti presenti nel database
     *
     * @return una List<Cliente> contenente i clienti presenti nel database
     */
    public List<Cliente> getClienti() {
        return clienteRepository.findAll();
    }

    /**
     * Aggiunge un nuovo cliente nel databse
     *
     * @param cliente cliente che si vuole aggiungere nel database
     */
    public void addNewCliente(Cliente cliente) {
        Optional<Cliente> clientetByEmailOptional = clienteRepository.findClienteByEmail(cliente.getEmail());
        if (clientetByEmailOptional.isPresent()) {
            throw new IllegalStateException("Email gia' in uso");
        }

        Optional<Cliente> clienteByNumCellulareOptional = clienteRepository.findClienteByNumCellulare(cliente.getNumCellulare());
        if (clienteByNumCellulareOptional.isPresent()) {
            throw new IllegalStateException("Numero di cellulare gia' in uso");
        }

        clienteRepository.save(cliente);
    }

    /**
     * Elimina un cliente con lo specifico clienteId dal database
     *
     * @param clienteId id del cliente che si vuole eliminare
     */
    public void deleteCliente(Long clienteId) {
        boolean exists = clienteRepository.existsById(clienteId);

        if (!exists) {
            throw new ResourceNotFoundException("cliente con id " + clienteId + " non esiste!");
        }
        clienteRepository.deleteById(clienteId);
    }

    /**
     * Metodo che aggiorna l'email del cliente con lo specifico id nel database
     *
     * @param clienteId id del cliente a cui si vuole modificare l'email
     * @param email     nuova email da impostare al cliente
     */
    @Transactional
    public void updateClienteEmail(Long clienteId, String email) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException("cliente con id " + clienteId + " non esiste!"));

        if (email != null && email.length() > 0 &&
                !Objects.equals(cliente.getEmail(), email)) {
            Optional<Cliente> clienteOptional = clienteRepository.findClienteByEmail(email);
            if (clienteOptional.isPresent()) {
                throw new IllegalStateException("email gia' in uso");
            }
            cliente.setEmail(email);
        }
    }

    /**
     * Ottiene un certo cliente dal database, altrimenti lancia un'eccezione ResourceNotFoundException
     *
     * @param clienteId id del cliente che si vuole ottenere
     * @return cliente che si vuole ottenere
     */
    public Optional<Cliente> getClienteById(Long clienteId) {
        return clienteRepository.findById(clienteId);
    }
}
