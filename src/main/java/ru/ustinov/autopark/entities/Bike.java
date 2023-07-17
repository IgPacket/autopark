package ru.ustinov.autopark.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Getter
@Setter
@Entity
@Builder
@Table(name = "bikes")
@AllArgsConstructor
public class Bike {
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

    @NotNull
    @Column(name = "manufacture_year", nullable = false)
    private Integer manufactureYear;

    @Override
    public String toString() {
        return "Марка: " + brand +
                ", Тип двигателя: " + engine +
                ", Год выпуска: " + manufactureYear;
    }
    public Bike() {
    }
}