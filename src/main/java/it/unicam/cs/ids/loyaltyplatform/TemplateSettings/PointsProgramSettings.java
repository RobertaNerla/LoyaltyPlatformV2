package it.unicam.cs.ids.loyaltyplatform.TemplateSettings;

import it.unicam.cs.ids.loyaltyplatform.FDTemplates.FidelityProgramType;

import java.util.Arrays;

public class PointsProgramSettings implements TemplateSettings{
    /**
     * This variable represents the ratio POINTS/EURO.
     * For example a ratio value of 2 represents 10 points for every 5 euros spent.
     */
    private double pointsEurRatio;

    private  final FidelityProgramType typeProgram;

    public PointsProgramSettings() {
        this.typeProgram = FidelityProgramType.POINTS;
    }

    //TODO: aggiungere catalogo premi? Ancora da analizzare


    //TODO: da finire di implementare
    @Override
    public boolean isValidSettings() {
        return this.pointsEurRatio > 0;
    }

    @Override
    public FidelityProgramType getTypeProgram() {
        return this.typeProgram;
    }

    @Override
    public void setSettingsFromString(String ... data) {
        this.pointsEurRatio = Arrays.stream(data).map(Double::parseDouble).findFirst().orElse(0.0);
    }



    public double getPointsEurRatio() {
        return pointsEurRatio;
    }

    public void setPointsEurRatio(double pointsEurRatio) {
        this.pointsEurRatio = pointsEurRatio;
    }


}
