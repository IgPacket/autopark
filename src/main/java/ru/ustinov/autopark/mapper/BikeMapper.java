package ru.ustinov.autopark.mapper;

import org.mapstruct.*;
import ru.ustinov.autopark.dto.BikeDTO;
import ru.ustinov.autopark.model.Bike;
import ru.ustinov.autopark.model.Brand;
import ru.ustinov.autopark.model.Engine;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface BikeMapper {
    Bike toEntity(BikeDTO bikeDTO, Brand brand, Engine engine);
    BikeDTO toDto(Bike bike);
}