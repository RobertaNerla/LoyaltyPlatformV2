package it.unicam.cs.ids.loyaltyplatform.FidelityProgram;

import it.unicam.cs.ids.loyaltyplatform.TemplateSettings.TemplateSettings;

public interface FidelityProgram<S extends TemplateSettings> {
    /**
     * Returns the fidelity program settings.
     * @return the fidelity program settings.
     */
    S getSettings();

    /**
     * Changes the fidelity program settings with the given data
     * @param data the given data
     */
    void setSettings(String ... data);

    //TODO: nelle prossime fasi di analisi e progettazione devono emergere altri dettagli per questa interfaccia

}
