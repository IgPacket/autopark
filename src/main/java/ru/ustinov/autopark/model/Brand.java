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
@Table(name = "brands")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "brand", nullable = false, length = 100)
    private String brand;

    @OneToMany(
            mappedBy = "brand",
            cascade = CascadeType.ALL
    )
    private Set<Bike> bikes = new LinkedHashSet<>();

    @OneToMany(
            mappedBy = "brand",
            cascade = CascadeType.ALL)
    private Set<Car> cars = new LinkedHashSet<>();

    @Override
    public String toString() {
        return brand;
    }
}