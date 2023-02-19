package it.unicam.cs.ids.loyaltyplatform.convalida;

import it.unicam.cs.ids.loyaltyplatform.sottoscrizione.Sottoscrizione;

public interface Tracker {

    void update(Sottoscrizione sottoscrizione, double importo);


}
