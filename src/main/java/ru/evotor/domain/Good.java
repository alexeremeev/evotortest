package ru.evotor.domain;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Good {
    @NotBlank(message = "Good name cannot be blank")
    private String name;
    @NotNull(message = "Good must have unit")
    private Unit unit;

    public Good() {
    }

    public Good(@NotBlank(message = "Good name cannot be blank") String name, @NotNull(message = "Good must have unit") Unit unit) {
        this.name = name;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
