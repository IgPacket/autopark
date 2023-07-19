package ru.ustinov.autopark.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

/**
 * DTO for {@link ru.ustinov.autopark.model.Car}
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarDTO implements Serializable {
    private String brand;
    private String engine;

    @NotNull(message = "Заполните vin-code")
    @Size(message = "vin-code состоит из 17 символов", min = 17, max = 17)
    @Pattern(message = "vin-code не соответствует стандартам", regexp = "^[A-HJ-NPR-Za-hj-npr-z\\d]{8}[\\dX][A-HJ-NPR-Za-hj-npr-z\\d]{2}\\d{6}$")
    private String vinCode;
    @NotNull(message = "Заполните год")
    @Range(message = "Введите год в диапазоне 1885-2023", min = 1885, max = 2023)
    private Integer manufactureYear;
}