package it.unicam.cs.ids.loyaltyplatform.convalida;

import it.unicam.cs.ids.loyaltyplatform.azienda.Azienda;
import it.unicam.cs.ids.loyaltyplatform.azienda.AziendaService;
import it.unicam.cs.ids.loyaltyplatform.cliente.Cliente;
import it.unicam.cs.ids.loyaltyplatform.cliente.ClienteService;
import it.unicam.cs.ids.loyaltyplatform.dto.TransazioneDto;
import it.unicam.cs.ids.loyaltyplatform.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransazioneService {
    private final TransazioneRepository transazioneRepository;

    private final ClienteService clienteService;
    private final AziendaService aziendaService;
    @Autowired
    public TransazioneService(TransazioneRepository transazioneRepository, ClienteService clienteService, AziendaService aziendaService) {
        this.transazioneRepository = transazioneRepository;
        this.clienteService = clienteService;
        this.aziendaService = aziendaService;
    }
    @GetMapping
    public List<Transazione> getTransazioni() {
        return transazioneRepository.findAll();
    }

    @PostMapping
    public Transazione addNewTransazione(TransazioneDto dto) throws ResourceNotFoundException {
        Azienda currentAzienda = getAziendaById(dto.getAziendaId());
        Cliente currentCliente = getClienteById(dto.getClienteId());
        if(checkIfIsClienteOfAzienda(currentCliente, currentAzienda)){
            Date currentDate = getCurrentDate();
            Transazione newTransazione= new Transazione(currentCliente, currentAzienda, dto.getImporto(), currentDate);
            return transazioneRepository.save(newTransazione);
        } else {
            //TODO: forse qui andrebbe creata una nuova eccezione con HttpStatus.UNAUTHORIZED
            throw new ResourceNotFoundException("Cliente non è sottoscritto a nessun programma fedeltà dell'azienda");
        }

    }

    private boolean checkIfIsClienteOfAzienda(Cliente currentCliente, Azienda currentAzienda) {
        //TODO: completare
        return true;
    }

    private Cliente getClienteById(Long clienteId) {
        Optional<Cliente> optionalCliente = clienteService.getClienteById(clienteId);
        if (optionalCliente.isEmpty()) {
            throw new ResourceNotFoundException("Cliente con id " + clienteId + "non esiste!");
        } else {
            return optionalCliente.get();
        }
    }

    private Azienda getAziendaById(Long aziendaId) {
        Optional<Azienda> optionalAzienda = aziendaService.getAziendaById(aziendaId);
        if (optionalAzienda.isEmpty()) {
            throw new ResourceNotFoundException("Azienda con id " + aziendaId + "non esiste!");
        } else {
            return optionalAzienda.get();
        }
    }

    private Date getCurrentDate(){
        LocalDateTime now = LocalDateTime.now();
        return Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
    }
}
