package ru.ustinov.autopark.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "engines")
public class Engine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "engine", nullable = false, length = 50)
    private String engine;

    @OneToMany(
            mappedBy = "engine",
            cascade = CascadeType.ALL)
    private Set<Bike> bikes = new LinkedHashSet<>();

    @OneToMany(
            mappedBy = "engine",
            cascade = CascadeType.ALL)
    private Set<Car> cars = new LinkedHashSet<>();

    @Override
    public String toString() {
        return engine;
    }
}