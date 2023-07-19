package ru.ustinov.autopark.mapper;

import org.mapstruct.*;
import ru.ustinov.autopark.dto.CarDTO;
import ru.ustinov.autopark.model.Brand;
import ru.ustinov.autopark.model.Car;
import ru.ustinov.autopark.model.Engine;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CarMapper {
    Car toEntity(CarDTO carDTO, Brand brand, Engine engine);
    CarDTO toDto(Car car);

}