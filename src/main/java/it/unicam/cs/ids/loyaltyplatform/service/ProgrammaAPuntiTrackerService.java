package it.unicam.cs.ids.loyaltyplatform.service;

import it.unicam.cs.ids.loyaltyplatform.dao.ProgrammaAPuntiTrackerRepository;
import it.unicam.cs.ids.loyaltyplatform.tracker.ProgrammaAPuntiTracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Service
public class ProgrammaAPuntiTrackerService {

    ProgrammaAPuntiTrackerRepository programmaAPuntiTrackerRepository;

    @Autowired
    public ProgrammaAPuntiTrackerService(ProgrammaAPuntiTrackerRepository programmaAPuntiTrackerRepository) {
        this.programmaAPuntiTrackerRepository = programmaAPuntiTrackerRepository;
    }

    public ProgrammaAPuntiTracker addNewProgrammaAPuntiTracker(Long clienteId, Long programId) {
        Optional<ProgrammaAPuntiTracker> trackerByClienteIdAndProgrammaId = programmaAPuntiTrackerRepository.
    }
}
