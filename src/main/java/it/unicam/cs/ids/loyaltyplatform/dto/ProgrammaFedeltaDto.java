package it.unicam.cs.ids.loyaltyplatform.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "tipo")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ProgrammaAPuntiDTO.class, name = "punti")})
//aggiungere altri subtypes per gestire altri tipi di programmi
public interface ProgrammaFedeltaDto {

    Long getAziendaId();
    String getNome();

}
