package com.gade;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

public class Pizza {

    @Id
    private Long id;

    @NotNull
    private String name;

    public Pizza() { }

    private Pizza(Builder builder) {
        id = builder.id;
        name = builder.name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static final class Builder {
        private Long id;
        private @NotNull String name;

        public Builder withName(@NotNull String name) {
            this.name = name;
            return this;
        }

        public Pizza build() {
            return new Pizza(this);
        }
    }
}
