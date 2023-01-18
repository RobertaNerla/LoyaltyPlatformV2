package it.unicam.cs.ids.loyaltyplatform.FDTemplates;

import it.unicam.cs.ids.loyaltyplatform.TemplateSettings.TemplateSettings;

import java.util.List;
/**
 * Inferface representing a Fidelity Program template
 */

public interface FDTemplate <S extends TemplateSettings> {
    /**
     * Returns the unique identifier of the template describing a loyalty program
     * @return id of a template
     */
    public String getTemplateId();

    /**
     * Returns the settings needed to create a loyalty program with this template
     * @return the settings needed to create a loyalty program with this template
     */
    public S getSettings();

    /**
     * Returns a short description of the loyalty program template
     * @return a short description of the loyalty program template
     */
    public String getDescription();

    /**
     * Returns the fidelity program type
     * @return the fidelity program type
     */
    FidelityProgramType getTypeProgram();

}
