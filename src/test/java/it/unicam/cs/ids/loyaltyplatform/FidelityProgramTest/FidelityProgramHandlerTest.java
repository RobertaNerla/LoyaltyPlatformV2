package it.unicam.cs.ids.loyaltyplatform.FidelityProgramTest;

import it.unicam.cs.ids.loyaltyplatform.FDPrograms.FDTemplates.FidelityProgramType;
import it.unicam.cs.ids.loyaltyplatform.FDPrograms.FDTemplates.SimpleFDTemplate;
import it.unicam.cs.ids.loyaltyplatform.FDPrograms.FDTemplates.TemplatesCatalog;
import it.unicam.cs.ids.loyaltyplatform.FDPrograms.FidelityProgram.FidelityProgramHandler;
import it.unicam.cs.ids.loyaltyplatform.FDPrograms.TemplateSettings.CashbackProgramSettings;
import it.unicam.cs.ids.loyaltyplatform.FDPrograms.TemplateSettings.PointsProgramSettings;
import it.unicam.cs.ids.loyaltyplatform.FDPrograms.TemplateSettings.TemplateSettings;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FidelityProgramHandlerTest {
    FidelityProgramHandler<SimpleFDTemplate<TemplateSettings>, TemplateSettings> controller;
    TemplatesCatalog<SimpleFDTemplate<TemplateSettings>,TemplateSettings> templatesCatalog = new TemplatesCatalog<>();


    @Test
    void shouldSetChosenTemplateOnController(){
        setupController();
        controller.setChosenTemplateId("2");
        assertEquals(FidelityProgramType.CASHBACK, controller.getCurrentTemplateDescription().getTypeProgram());
    }

    @Test
    void shouldSetSettings(){
        setupController();
        controller.setChosenTemplateId("1");
        assertTrue(controller.setTemplateData("2"));
    }


    private void setupController(){
        templatesCatalog.getFDTemplate().add(new SimpleFDTemplate<>("1", PointsProgramSettings::new, FidelityProgramType.POINTS));
        templatesCatalog.getFDTemplate().add(new SimpleFDTemplate<>("2", CashbackProgramSettings::new, FidelityProgramType.CASHBACK));
        controller = new FidelityProgramHandler<>(templatesCatalog);
    }
}
