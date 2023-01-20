package it.unicam.cs.ids.loyaltyplatform.FDPrograms.FDTemplates;

import it.unicam.cs.ids.loyaltyplatform.FDPrograms.TemplateSettings.TemplateSettings;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TemplatesCatalog <D extends FDTemplate<S>, S extends TemplateSettings> {
    /**
     * This list contains all the templates defined in Loyalty Platform
     */
    private List<D> templateDList;


    public TemplatesCatalog(){
        this.templateDList = new ArrayList<>();
    }

    public TemplatesCatalog(List<D> templateDList){
        this.templateDList = templateDList;
    }

    public List<D> getFDTemplate(){
        return templateDList;
    }


    public D getChosenTemplate(String id){
        return getChosen(id).orElse(null);
    }

    private Optional<D> getChosen(String templateId) {
        return templateDList.stream()
                .filter(td -> templateId.equals(td.getTemplateId()))
                .findFirst();
    }
    //TODO: se lasciamo le descrizioni questo metodo non serve
    public TemplateSettings getChosenTemplateSettings(String templateId){
        return getChosen(templateId)
                .map(FDTemplate::getSettings)
                .orElse(null);
    }

    public List<D> getTemplateDList() {
        return templateDList;
    }

    public void setTemplateDList(List<D> templateDList) {
        this.templateDList = templateDList;
    }
}
