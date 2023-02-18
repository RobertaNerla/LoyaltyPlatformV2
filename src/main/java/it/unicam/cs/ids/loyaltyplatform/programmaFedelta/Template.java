package it.unicam.cs.ids.loyaltyplatform.programmaFedelta;

/**
 * Questa classe serve per rappresentare i template disponibili in LoyaltyPlatform
 */
public class Template {

    private final TipologiaProgramma tipo;

    private String descrizione;


    public Template(TipologiaProgramma tipo) {
        this.tipo = tipo;
    }

    public Template(TipologiaProgramma tipo, String descrizione){
        this.tipo=tipo;
        this.descrizione = descrizione;
    }

    public TipologiaProgramma getTipo() {
        return tipo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
