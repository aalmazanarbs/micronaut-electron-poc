package com.gade;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PizzaRepository extends ReactiveCrudRepository<Pizza, Long> {
}
