package com.gade;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import reactor.core.publisher.Flux;

import javax.inject.Inject;

@Controller("/pizza")
public class PizzaController {

    private final PizzaRepository pizzaRepository;

    @Inject
    public PizzaController(final PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @Get
    public Flux<Pizza> getAll() {
        return pizzaRepository.findAll();
    }
}