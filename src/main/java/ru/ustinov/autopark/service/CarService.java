package ru.ustinov.autopark.service;

import jakarta.validation.constraints.NotNull;
import org.springframework.transaction.annotation.Transactional;
import ru.ustinov.autopark.dto.CarDTO;
import ru.ustinov.autopark.model.Car;
import ru.ustinov.autopark.model.Engine;

import java.util.List;

public interface CarService {
    Car findById(int id);
    CarDTO findDTOById(int id);
    Car findByVinCode(String vinCode);
    CarDTO findDTOByVinCode(String vinCode);
    List<CarDTO> findAll();
    Integer countAllBy();
    Integer countAllByBrand(String brand);
    List<CarDTO> findAllByBrand(String brand);
    Integer countAllByEngine(String engine);
    List<CarDTO> findAllByEngine(String engine);
    Integer countAllByManufactureYear(@NotNull Integer manufactureYear);
    List<CarDTO> findAllByManufactureYear(@NotNull Integer manufactureYear);
    List<CarDTO> findAllByManufactureYearLessThan(@NotNull Integer manufactureYear);
    Integer countAllByManufactureYearLessThan(@NotNull Integer manufactureYear);
    List<CarDTO> findAllByManufactureYearGreaterThan(@NotNull Integer manufactureYear);
    Integer countAllByManufactureYearGreaterThan(@NotNull Integer manufactureYear);
    List<CarDTO> findAllByManufactureYearBetween(@NotNull Integer manufactureYear, @NotNull Integer manufactureYear2);
    Integer countAllByManufactureYearBetween(@NotNull Integer manufactureYear, @NotNull Integer manufactureYear2);
    @Transactional
    void save(CarDTO carDTO);
    @Transactional
    void updateEngine(Car updatedCar, Engine newEngine);
    @Transactional
    void updateEngineById(Integer id, Engine newEngine);
    @Transactional
    void updateEngineByVinCode(String vinCode, Engine newEngine);
    @Transactional
    void deleteCarById(Integer id);
    @Transactional
    void deleteCarByVinCode(String vinCode);
}
