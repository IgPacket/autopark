package ru.ustinov.autopark.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "engines")
public class Engine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    //@JsonIgnore
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "engine", nullable = false, length = 50)
    private String engine;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "engine",
            cascade = CascadeType.ALL)
    //@JsonIgnore
    private Set<Bike> bikes = new LinkedHashSet<>();

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "engine",
            cascade = CascadeType.ALL)
    //@JsonIgnore
    private Set<Car> cars = new LinkedHashSet<>();

    public Engine() {
    }

    public Engine(String engine) {
        this.engine = engine;
    }

    @Override
    public String toString() {
        return engine;
    }
}