package com.gade;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

public class Pizza {

    @Id
    private Long id;

    @NotNull
    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
