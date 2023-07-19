package ru.ustinov.autopark.service;

import jakarta.validation.constraints.NotNull;
import org.springframework.transaction.annotation.Transactional;
import ru.ustinov.autopark.dto.BikeDTO;
import ru.ustinov.autopark.model.Bike;
import ru.ustinov.autopark.model.Engine;

import java.util.List;

public interface BikeService {
    Bike findById(Integer id);
    BikeDTO findDTOById(Integer id);
    List<BikeDTO> findAll();
    Integer countAllBy();
    List<BikeDTO> findAllByBrand(String brand);
    Integer countAllByBrand(String brand);
    List<BikeDTO> findAllByEngine(String engine);
    Integer countAllByEngine(String engine);
    List<BikeDTO> findAllByManufactureYear(@NotNull Integer manufactureYear);
    Integer countAllByManufactureYear(@NotNull Integer manufactureYear);
    List<BikeDTO> findAllByManufactureYearLessThan(@NotNull Integer manufactureYear);
    Integer countAllByManufactureYearLessThan(@NotNull Integer manufactureYear);
    List<BikeDTO> findAllByManufactureYearGreaterThan(@NotNull Integer manufactureYear);
    Integer countAllByManufactureYearGreaterThan(@NotNull Integer manufactureYear);
    List<BikeDTO> findAllByManufactureYearBetween(@NotNull Integer manufactureYear, @NotNull Integer manufactureYear2);
    Integer countAllByManufactureYearBetween(@NotNull Integer manufactureYear, @NotNull Integer manufactureYear2);
    @Transactional
    void save(BikeDTO bikeDTO);
    @Transactional
    void updateEngineById(Integer id, Engine engine);
    @Transactional
    void deleteBikeById(Integer id);
}
