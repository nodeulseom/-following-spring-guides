package com.example.buildingrestfulwebservice;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuoteResource {

    private Quote value;
    private String type;
}
