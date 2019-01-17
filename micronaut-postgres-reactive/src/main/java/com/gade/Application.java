package com.gade;

import io.micronaut.runtime.Micronaut;
// import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

// @EnableJdbcRepositories
public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class);
    }
}