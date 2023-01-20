package it.unicam.cs.ids.loyaltyplatform.FDTemplatesTest;

import it.unicam.cs.ids.loyaltyplatform.FDPrograms.FDTemplates.FidelityProgramType;
import it.unicam.cs.ids.loyaltyplatform.FDPrograms.FDTemplates.SimpleFDTemplate;
import it.unicam.cs.ids.loyaltyplatform.FDPrograms.FDTemplates.TemplatesCatalog;
import it.unicam.cs.ids.loyaltyplatform.FDPrograms.TemplateSettings.CashbackProgramSettings;
import it.unicam.cs.ids.loyaltyplatform.FDPrograms.TemplateSettings.PointsProgramSettings;
import it.unicam.cs.ids.loyaltyplatform.FDPrograms.TemplateSettings.TemplateSettings;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TemplatesCatalogTest {

    TemplatesCatalog<SimpleFDTemplate<TemplateSettings>,TemplateSettings> templatesCatalog = new TemplatesCatalog<>();

    @Test
    void shouldAddTemplateToCatalog(){
        addPointsAndCashbackTemplateToCatalog();
        assertEquals(2, templatesCatalog.getFDTemplate().size());
    }

    @Test
    void shouldGetChosenTemplate(){
        addPointsAndCashbackTemplateToCatalog();
        assertEquals(FidelityProgramType.CASHBACK, templatesCatalog.getChosenTemplate("2").getTypeProgram());

    }

    private void addPointsAndCashbackTemplateToCatalog(){
        templatesCatalog.getFDTemplate().add(new SimpleFDTemplate<>("1", PointsProgramSettings::new, FidelityProgramType.POINTS));
        templatesCatalog.getFDTemplate().add(new SimpleFDTemplate<>("2", CashbackProgramSettings::new, FidelityProgramType.CASHBACK));
    }

}
