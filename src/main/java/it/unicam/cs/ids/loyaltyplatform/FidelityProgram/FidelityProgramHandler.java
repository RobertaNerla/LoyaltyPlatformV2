package it.unicam.cs.ids.loyaltyplatform.FidelityProgram;

import it.unicam.cs.ids.loyaltyplatform.FDTemplates.FDTemplate;
import it.unicam.cs.ids.loyaltyplatform.FDTemplates.TemplatesCatalog;
import it.unicam.cs.ids.loyaltyplatform.TemplateSettings.TemplateSettings;

public class FidelityProgramHandler<D extends FDTemplate<S>,S extends TemplateSettings> {
    private final TemplatesCatalog<D,S> templatesCatalog;
    private D currentTemplateDescription;
    private S currentTemplateSettings;


    public FidelityProgramHandler(){
        this.templatesCatalog = new TemplatesCatalog<>();
    }

    public FidelityProgramHandler(TemplatesCatalog<D,S> templatesCatalog) {
        this.templatesCatalog = templatesCatalog;
    }

    public void setChosenTemplateId(String id){
        this.currentTemplateDescription = templatesCatalog.getChosenTemplate(id);
        this.currentTemplateSettings = currentTemplateDescription.getSettings();
    }

    public void getChosenTemplateSettings(){
        this.currentTemplateSettings = this.currentTemplateDescription.getSettings();
    }

    //TODO: come riceve questi dati il controller?
    public boolean setTemplateData(String ... data){
        currentTemplateSettings.setSettingsFromString(data);
        return currentTemplateSettings.isValidSettings();
    }

    public TemplatesCatalog<D, S> getTemplatesCatalog() {
        return templatesCatalog;
    }

    public D getCurrentTemplateDescription() {
        return currentTemplateDescription;
    }

    public void setCurrentTemplateDescription(D currentTemplateDescription) {
        this.currentTemplateDescription = currentTemplateDescription;
    }

    public S getCurrentTemplateSettings() {
        return currentTemplateSettings;
    }

    public void setCurrentTemplateSettings(S currentTemplateSettings) {
        this.currentTemplateSettings = currentTemplateSettings;
    }
}
