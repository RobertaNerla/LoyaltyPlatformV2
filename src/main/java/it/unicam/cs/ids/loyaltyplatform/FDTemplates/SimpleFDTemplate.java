package it.unicam.cs.ids.loyaltyplatform.FDTemplates;

import it.unicam.cs.ids.loyaltyplatform.TemplateSettings.TemplateSettings;

import java.util.function.Supplier;

public class SimpleFDTemplate<S extends TemplateSettings> implements FDTemplate<S> {

    private final String templateID;
    private String description;
    private final Supplier<S> settingsCreator;
    private final FidelityProgramType typeFD;

    public SimpleFDTemplate(String templateID, Supplier<S> settingsCreator, FidelityProgramType typeFD) {
        this.settingsCreator = settingsCreator;
        this.templateID = templateID;
        this.typeFD = typeFD;
    }

    public SimpleFDTemplate(String templateID, String description, Supplier<S> settingsCreator, FidelityProgramType typeFD){
        this(templateID, settingsCreator, typeFD);
        this.description = description;
    }


    @Override
    public String getTemplateId() {
        return this.templateID;
    }

    @Override
    public S getSettings() {
        return settingsCreator.get();
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public FidelityProgramType getTypeProgram() {
        return this.typeFD;
    }

}
