package ru.ustinov.autopark.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

/**
 * DTO for {@link ru.ustinov.autopark.entities.Bike}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BikeDTO implements Serializable {
    private String brand;
    private String engine;
    @NotNull(message = "Заполните год")
    @Range(message = "Введите год в диапазоне", min = 1885, max = 2023)
    private Integer manufactureYear;
}