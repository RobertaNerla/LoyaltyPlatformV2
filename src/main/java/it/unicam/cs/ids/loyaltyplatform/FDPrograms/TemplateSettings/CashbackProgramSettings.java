package it.unicam.cs.ids.loyaltyplatform.FDPrograms.TemplateSettings;

import it.unicam.cs.ids.loyaltyplatform.FDPrograms.FDTemplates.FidelityProgramType;

import java.util.Arrays;

public class CashbackProgramSettings implements TemplateSettings {
    /**
     * This variable represents the ratio POINTS/EURO.
     * For example a ratio value of 2 represents 10 points for every 5 euros spent.
     */
    private double cashbackEurRatio;

    private  final FidelityProgramType typeProgram;

    public CashbackProgramSettings() {
        this.typeProgram = FidelityProgramType.CASHBACK;
    }

    //TODO: aggiungere catalogo premi? Ancora da analizzare


    //TODO: da finire di implementare
    @Override
    public boolean isValidSettings() {
        return this.cashbackEurRatio > 0;
    }

    @Override
    public FidelityProgramType getTypeProgram() {
        return this.typeProgram;
    }

    @Override
    public void setSettingsFromString(String ... data){
        this.cashbackEurRatio = Arrays.stream(data).map(Double::parseDouble).findFirst().orElse(0.0);
    }

    public double getCashbackEurRatio() {
        return cashbackEurRatio;
    }

    public void setCashbackEurRatio(double cashbackEurRatio) {
        this.cashbackEurRatio = cashbackEurRatio;
    }
}
