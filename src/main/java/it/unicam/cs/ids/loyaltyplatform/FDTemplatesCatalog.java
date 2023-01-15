package it.unicam.cs.ids.loyaltyplatform;

import it.unicam.cs.ids.loyaltyplatform.FDTemplates.FDTemplate;

import java.util.ArrayList;
import java.util.List;

public class FDTemplatesCatalog {
    List<FDTemplate> templatesList;

    public FDTemplatesCatalog(){
        this.templatesList = new ArrayList<FDTemplate>();
    }

    public List<FDTemplate> getTemplateDescriptions(){
        return templatesList;
    }

    public  String getChosenTemplateDescription(String templateId){
        String description = "";
        for (FDTemplate template: templatesList ) {
            if(template.getTemplateId() == templateId)
                description = template.getDescription();
        }

        //comment for a commit
        return description;
    }
}