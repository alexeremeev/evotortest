package ru.evotor.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
@Entity
@Table(name = "units")
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Unit name cannot be blank")
    private String name;

    public Unit() {

    }

    public Unit(@NotBlank(message = "Unit name cannot be blank") String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
