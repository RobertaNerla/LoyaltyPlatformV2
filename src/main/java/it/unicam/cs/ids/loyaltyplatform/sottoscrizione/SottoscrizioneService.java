package it.unicam.cs.ids.loyaltyplatform.sottoscrizione;

import it.unicam.cs.ids.loyaltyplatform.cliente.Cliente;
import it.unicam.cs.ids.loyaltyplatform.cliente.ClienteService;
import it.unicam.cs.ids.loyaltyplatform.exception.ResourceNotFoundException;
import it.unicam.cs.ids.loyaltyplatform.premio.Premio;
import it.unicam.cs.ids.loyaltyplatform.programmaFedelta.ProgrammaAPunti;
import it.unicam.cs.ids.loyaltyplatform.programmaFedelta.ProgrammaFedelta;
import it.unicam.cs.ids.loyaltyplatform.programmaFedelta.ProgrammaFedeltaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Service
public class SottoscrizioneService {
    private final SottoscrizioneRepository sottoscrizioneRepository;
    private final ClienteService clienteService;
    private final ProgrammaFedeltaService programmaFedeltaService;
    private final SubFactory subFactory;


    public SottoscrizioneRepository getSottoscrizioneRepository() {
        return sottoscrizioneRepository;
    }

    @Autowired
    public SottoscrizioneService(SottoscrizioneRepository sottoscrizioneRepository,
                                 ClienteService clienteService,
                                 ProgrammaFedeltaService programmaFedeltaService,
                                 SubFactory subFactory) {
        this.sottoscrizioneRepository = sottoscrizioneRepository;
        this.clienteService = clienteService;
        this.programmaFedeltaService = programmaFedeltaService;
        this.subFactory = subFactory;
    }

    @GetMapping
    public List<Sottoscrizione> getSottoscrizioni() {
        return sottoscrizioneRepository.findAll();
    }

    @PostMapping
    public Sottoscrizione addNewSottoscrizione(Long clienteId, Long programmaId) throws ResourceNotFoundException {
        Cliente currentClient = getClienteById(clienteId);
        ProgrammaFedelta currentProgram = getProgrammaById(programmaId);
        Sottoscrizione newSub = subFactory.submit(currentClient, currentProgram);

        programmaFedeltaService.addNewSottoscrizione(currentProgram, newSub);
        clienteService.aggiungiNuovaSottoscrizione(currentClient, newSub);
        programmaFedeltaService.incrementaNumClienti(currentProgram);
        return sottoscrizioneRepository.save(newSub);
    }

    private Cliente getClienteById(Long clienteId) throws ResourceNotFoundException {
        Optional<Cliente> optionalCliente = clienteService.getClienteById(clienteId);
        if (optionalCliente.isEmpty()) {
            throw new ResourceNotFoundException("Cliente con id " + clienteId + "non esiste!");
        } else {
            return optionalCliente.get();
        }
    }

    private ProgrammaFedelta getProgrammaById(Long programmaId) throws ResourceNotFoundException {
        Optional<ProgrammaFedelta> optionalProgramma = programmaFedeltaService.getProgrammaById(programmaId);
        if (optionalProgramma.isEmpty()) {
            throw new ResourceNotFoundException("Programma fedeltà con id " + programmaId + "non esiste!");
        } else {
            return optionalProgramma.get();
        }
    }

    public Sottoscrizione getSottoscrizioneByProgramIdAndClientId(Long programId, Cliente cliente) throws ResourceNotFoundException {
        Optional<Sottoscrizione> optionalSub = sottoscrizioneRepository.findSottoscrizioneByClienteAndProgramma(cliente, getProgrammaById(programId));
        if (optionalSub.isEmpty()) {
            throw new ResourceNotFoundException("Il cliente " + cliente.getClienteId() + " non è sottoscritto al programma " + programId + ".");
        } else {
            return optionalSub.get();
        }
    }

    public void saveChangedSottoscrizione(SottoscrizioneProgrammaAPunti sottoscrizione) {
        sottoscrizioneRepository.save(sottoscrizione);
    }

    public void deleteSottoscrizione(Long sottoscrizioneId) {
        Optional<Sottoscrizione> sottoscrizioneOptional = sottoscrizioneRepository.findById(sottoscrizioneId);
        if (sottoscrizioneOptional.isEmpty()) {
            throw new ResourceNotFoundException("La sottoscrizione con l'id " + sottoscrizioneId +
                    "non esiste!");
        }

        sottoscrizioneRepository.deleteById(sottoscrizioneId);
    }

    private Sottoscrizione getSottoscrizioneById(Long sottoscrizioneId){
        Optional<Sottoscrizione> optionalSottoscrizione= sottoscrizioneRepository.findById(sottoscrizioneId);
        if(optionalSottoscrizione.isEmpty()){
            throw new ResourceNotFoundException("La sottoscrizione " + sottoscrizioneId +" non esiste!");
        }else {
            return optionalSottoscrizione.get();
        }
    }

    private Premio getPremioFromProgrammaPunti(ProgrammaAPunti progPunti, Long premioId) {
        Optional<Premio> optionalPremio = progPunti.getPremioById(premioId);
        if (optionalPremio.isEmpty()) {
            throw new ResourceNotFoundException("Il premio scelto " + premioId + " non esiste");
        } else {
            return optionalPremio.get();
        }
    }



    public Sottoscrizione riscattaPremio(Long sottoscrizioneId, Long premioId) throws ResourceNotFoundException, IllegalArgumentException, RuntimeException {
        Sottoscrizione sottoscrizione = getSottoscrizioneById(sottoscrizioneId);
        if(sottoscrizione instanceof SottoscrizioneProgrammaAPunti subPunti){
            ProgrammaFedelta programma = subPunti.getProgramma();
            if(programma instanceof ProgrammaAPunti progPunti){
                Premio premioDaRiscattare = getPremioFromProgrammaPunti(progPunti,premioId);
                int puntiNecessari = premioDaRiscattare.getCostoPunti();
                int puntiDisponibili = subPunti.getPuntiAccumulati();
                if(puntiNecessari > puntiDisponibili){
                    throw new RuntimeException("Punti insufficienti per riscattare il premio");
                } else {
                    subPunti.setPuntiAccumulati(puntiDisponibili - puntiNecessari);
                    sottoscrizioneRepository.save(subPunti);
                    return subPunti;
                }
            } else{
                throw new IllegalArgumentException("Non è un programma a punti");
            }
        } else {
            throw new IllegalArgumentException("Non è una sottoscrizione a punti");
    }

    }
}