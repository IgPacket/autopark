package ru.ustinov.autopark.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bikes")
public class Bike {
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

    @NotNull
    @Column(name = "manufacture_year", nullable = false)
    private Integer manufactureYear;

    @Override
    public String toString() {
        return "Марка: " + brand +
                ", Тип двигателя: " + engine +
                ", Год выпуска: " + manufactureYear;
    }
}