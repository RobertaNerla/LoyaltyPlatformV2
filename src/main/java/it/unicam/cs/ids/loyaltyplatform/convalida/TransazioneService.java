package it.unicam.cs.ids.loyaltyplatform.convalida;

import it.unicam.cs.ids.loyaltyplatform.azienda.Azienda;
import it.unicam.cs.ids.loyaltyplatform.azienda.AziendaService;
import it.unicam.cs.ids.loyaltyplatform.cliente.Cliente;
import it.unicam.cs.ids.loyaltyplatform.cliente.ClienteService;
import it.unicam.cs.ids.loyaltyplatform.dto.TransazioneDto;
import it.unicam.cs.ids.loyaltyplatform.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<Transazione> getTransazioni() {
        return transazioneRepository.findAll();
    }

    public Transazione addNewTransazione(TransazioneDto dto) throws ResourceNotFoundException {
        Azienda currentAzienda = getAziendaById(dto.getAziendaId());
        Cliente currentCliente = getClienteById(dto.getClienteId());
        Date currentDate = getCurrentDate();
        Transazione newTransazione = new Transazione(currentCliente, currentAzienda, dto.getImporto(), currentDate);
        return transazioneRepository.save(newTransazione);
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

    private Date getCurrentDate() {
        LocalDateTime now = LocalDateTime.now();
        return Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
    }

    public List<Transazione> getTransazioniByAzienda(Long aziendaId) {
        return transazioneRepository.findAll().stream()
                .filter(transazione -> transazione.getAzienda().getAziendaId().equals(aziendaId))
                .collect(Collectors.toList());
    }

    public List<Transazione> getTransazioneByCliente(Long clienteId) {
        return transazioneRepository.findAll().stream()
                .filter(transazione -> transazione.getCliente().getClienteId().equals(clienteId))
                .collect(Collectors.toList());
    }
}
