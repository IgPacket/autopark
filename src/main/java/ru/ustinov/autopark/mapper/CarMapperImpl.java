package ru.ustinov.autopark.mapper;

import org.springframework.stereotype.Component;
import ru.ustinov.autopark.dto.CarDTO;
import ru.ustinov.autopark.model.Brand;
import ru.ustinov.autopark.model.Car;
import ru.ustinov.autopark.model.Engine;

@Component
public class CarMapperImpl implements CarMapper {
    @Override
    public Car toEntity(CarDTO carDTO, Brand brand, Engine engine) {
        return Car.builder()
                .brand(brand)
                .engine(engine)
                .vinCode(carDTO.getVinCode())
                .manufactureYear(carDTO.getManufactureYear())
                .build();
    }
    @Override
    public CarDTO toDto(Car car) {
        return CarDTO.builder()
                .brand(car.getBrand().getBrand())
                .engine(car.getEngine().getEngine())
                .vinCode(car.getVinCode())
                .manufactureYear(car.getManufactureYear())
                .build();
    }
}
