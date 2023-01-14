package it.unicam.cs.ids.loyaltyplatform.FDTemplates;

import java.util.List;
/**
 * Inferface representing a Fidelity Program template
 */

public interface FDTemplate {
    public String getTemplateId();
    public boolean isValidData();
    public List<Field> getFields();
    public String getDescription();
}
