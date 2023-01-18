package it.unicam.cs.ids.loyaltyplatform.FidelityProgram;

import it.unicam.cs.ids.loyaltyplatform.TemplateSettings.TemplateSettings;

public class SimpleFidelityProgram <S extends TemplateSettings> implements FidelityProgram<S> {

    private final S templateSettings;

    public SimpleFidelityProgram(S templateSettings) {
        this.templateSettings = templateSettings;
    }

    @Override
    public S getSettings() {
        return this.templateSettings;
    }

    @Override
    public void setSettings(String... data) {
        this.templateSettings.setSettingsFromString(data);
    }
}
