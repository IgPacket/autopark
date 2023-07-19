package ru.ustinov.autopark.mapper;

import org.springframework.stereotype.Component;
import ru.ustinov.autopark.dto.BikeDTO;
import ru.ustinov.autopark.model.Bike;
import ru.ustinov.autopark.model.Brand;
import ru.ustinov.autopark.model.Engine;

@Component
public class BikeMapperImpl implements BikeMapper {
    @Override
    public Bike toEntity(BikeDTO bikeDTO, Brand brand, Engine engine) {
        return Bike.builder()
                .brand(brand)
                .engine(engine)
                .manufactureYear(bikeDTO.getManufactureYear())
                .build();
    }
    @Override
    public BikeDTO toDto(Bike bike) {
        return BikeDTO.builder()
                .brand(bike.getBrand().getBrand())
                .engine(bike.getEngine().getEngine())
                .manufactureYear(bike.getManufactureYear())
                .build();
    }
}
