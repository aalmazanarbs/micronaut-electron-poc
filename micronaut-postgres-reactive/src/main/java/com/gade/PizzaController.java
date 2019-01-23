package com.gade;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

    @Post
    public Mono<Pizza> addOne(@Body PizzaRequest pizzaRequest) {
        return Mono.just(new Pizza.Builder().withName(pizzaRequest.getName()).build());
        // return pizzaRepository.save(new Pizza.Builder().withName(pizzaRequest.getName()).build());
    }
}