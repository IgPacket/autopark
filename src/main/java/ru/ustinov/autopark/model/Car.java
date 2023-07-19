package ru.ustinov.autopark.model;

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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne()
    @Cascade(CascadeType.MERGE)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne()
    @Cascade(CascadeType.MERGE)
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
}