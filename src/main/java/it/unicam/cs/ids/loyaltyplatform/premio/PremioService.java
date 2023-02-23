package it.unicam.cs.ids.loyaltyplatform.premio;

import it.unicam.cs.ids.loyaltyplatform.programmaFedelta.ProgrammaFedeltaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PremioService {

    private final ProgrammaFedeltaService programmaFedeltaService;
    @Autowired
    public PremioService(ProgrammaFedeltaService programmaFedeltaService) {
        this.programmaFedeltaService = programmaFedeltaService;
    }




}
