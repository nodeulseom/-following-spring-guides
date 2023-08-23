package com.example.buildingrestfulwebservice;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Quote {

    @Id @GeneratedValue
    private Long id;

    @JsonProperty
    private String quote;

    Quote(String quote) {
        this.quote = quote;
    }

    protected Quote() {}
}
