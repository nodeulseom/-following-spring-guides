package com.example.buildingrestfulwebservice;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class QuoteResource {

    @JsonProperty
    private String type;
    @JsonProperty
    private Quote value;

    public QuoteResource(String type, Quote value) {
        this.type = type;
        this.value = value;
    }
}
