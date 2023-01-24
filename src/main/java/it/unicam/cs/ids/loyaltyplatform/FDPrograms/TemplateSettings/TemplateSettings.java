package it.unicam.cs.ids.loyaltyplatform.FDPrograms.TemplateSettings;

import it.unicam.cs.ids.loyaltyplatform.FDPrograms.FDTemplates.FidelityProgramType;

public interface TemplateSettings {
    /**
     * Returns true if the entered settings are valid.
     * @return true if the entered settings are valid.
     */
    boolean isValidSettings();

    /**
     * Returns the fidelity program type
     * @return the fidelity program tipo
     */
    FidelityProgramType getTypeProgram();

    /**
     * Sets the template settings with the given data
     * @param data the given data
     */
    void setSettingsFromString(String... data);
}
