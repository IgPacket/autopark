package ru.ustinov.autopark.services;

import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ustinov.autopark.entities.Brand;
import ru.ustinov.autopark.entities.Car;
import ru.ustinov.autopark.entities.Engine;
import ru.ustinov.autopark.repositories.CarRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car findById(int id) {
        Optional<Car> foundCar = carRepository.findById(id);
        return foundCar.orElse(null);
    }
    public List<Car> findAll() {
        return carRepository.findAll();
    }
    @Transactional
    public void save(Car car) {
        carRepository.save(car);
    }

    @Transactional
    public void updateEngine(Integer id, Engine engine) {
        carRepository.updateEngine(id, engine);
    }
    @Transactional
    public void delete(Car car) {
        carRepository.delete(car);
    }
    public List<Car> findAllByBrand(Brand brand) {
        return carRepository.findAllByBrand(brand);
    }
    public List<Car> findAllByEngine(Engine engine) {
        return carRepository.findAllByEngine(engine);
    }

    public List<Car> findAllByManufactureYear(@NotNull Integer manufactureYear) {
        return carRepository.findAllByManufactureYear(manufactureYear);
    }

    public List<Car> findAllByManufactureYearGreaterThan(@NotNull Integer manufactureYear) {
        return carRepository.findAllByManufactureYearGreaterThan(manufactureYear);
    }

    public List<Car> findAllByManufactureYearLessThan(@NotNull Integer manufactureYear) {
        return carRepository.findAllByManufactureYearLessThan(manufactureYear);
    }

    public List<Car> findAllByManufactureYearBetween(@NotNull Integer manufactureYear,
                                                     @NotNull Integer manufactureYear2) {
        return carRepository.findAllByManufactureYearBetween(manufactureYear, manufactureYear2);
    }

    public List<Car> findAllByVinCode(String vinCode) {
        return carRepository.findAllByVinCode(vinCode);
    }

}
