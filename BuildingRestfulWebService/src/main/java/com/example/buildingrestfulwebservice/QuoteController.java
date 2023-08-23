package com.example.buildingrestfulwebservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
public class QuoteController {

    private static final Quote NONE = new Quote("None");
    private static final Random RANDOMIZER = new Random();

    private final QuoteRepository repository;

    public QuoteController(QuoteRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api")
    public List<QuoteResource> getAll() {
        return repository.findAll().stream()
                .map(quote -> new QuoteResource(quote, "succecss"))
                .collect(Collectors.toList());
    }

    @GetMapping("/api/{id}")
    public QuoteResource getOne(@PathVariable Long id) {
        return repository.findById(id)
                .map(quote -> new QuoteResource(quote, "success"))
                .orElse(new QuoteResource(NONE, "Quote " + id + " dose not exist"));
    }

    @GetMapping("/api/random")
    public QuoteResource getRandomOne() {
        return getOne(nextLong(1, repository.count() +1));
    }

    private long nextLong(long lowerRange, long upperRange) {
        return (long) (RANDOMIZER.nextDouble() * (upperRange - lowerRange)) + lowerRange;
    }
}
