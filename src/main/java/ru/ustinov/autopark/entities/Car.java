package ru.ustinov.autopark.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Getter
@Setter
@Entity
@Builder
@Table(name = "cars")
@AllArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "engine_id")
    private Engine engine;

    @Size(max = 100)
    @NotNull
    @Column(name = "vin_code", nullable = false, length = 100)
    private String vinCode;

    @NotNull
    @Column(name = "manufacture_year", nullable = false)
    private Integer manufactureYear;

    @Override
    public String toString() {
        return "Марка: " + brand +
                ", Тип двигателя: " + engine +
                ", Vin-code: " + vinCode + '\'' +
                ", Год выпуска: " + manufactureYear;
    }

    public Car() {

    }
}