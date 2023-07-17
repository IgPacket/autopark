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
@Table(name = "brands")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    //@JsonIgnore
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "brand", nullable = false, length = 100)
    private String brand;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "brand",
            cascade = CascadeType.ALL
    )
    //@JsonIgnore
    private Set<Bike> bikes = new LinkedHashSet<>();

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "brand",
            cascade = CascadeType.ALL)
    //@JsonIgnore
    private Set<Car> cars = new LinkedHashSet<>();

    public Brand() {
    }

    public Brand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return brand;
    }
}