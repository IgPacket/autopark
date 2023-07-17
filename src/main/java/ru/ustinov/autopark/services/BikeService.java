package ru.ustinov.autopark.services;

import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ustinov.autopark.entities.Bike;
import ru.ustinov.autopark.entities.Brand;
import ru.ustinov.autopark.entities.Engine;
import ru.ustinov.autopark.repositories.BikeRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BikeService {
    private final BikeRepository bikeRepository;

    public BikeService(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    public Bike findById(int id) {
        Optional<Bike> foundBike = bikeRepository.findById(id);
        return foundBike.orElse(null);
    }
    public List<Bike> findAll() {
        return bikeRepository.findAll();
    }
    @Transactional
    public void save(Bike bike) {
        bikeRepository.save(bike);
    }

    @Transactional
    public void updateEngine(Integer id, Engine engine) {
        bikeRepository.updateEngine(id, engine);
    }
    @Transactional
    public void delete(Bike bike) {
        bikeRepository.delete(bike);
    }
    public List<Bike> findAllByBrand(Brand brand) {
        return bikeRepository.findAllByBrand(brand);
    }
    public List<Bike> findAllByEngine(Engine engine) {
        return bikeRepository.findAllByEngine(engine);
    }

    public List<Bike> findAllByManufactureYear(@NotNull Integer manufactureYear) {
        return bikeRepository.findAllByManufactureYear(manufactureYear);
    }

    public List<Bike> findAllByManufactureYearGreaterThan(@NotNull Integer manufactureYear) {
        return bikeRepository.findAllByManufactureYearGreaterThan(manufactureYear);
    }

    public List<Bike> findAllByManufactureYearLessThan(@NotNull Integer manufactureYear) {
        return bikeRepository.findAllByManufactureYearLessThan(manufactureYear);
    }

    public List<Bike> findAllByManufactureYearBetween(@NotNull Integer manufactureYear,
                                                     @NotNull Integer manufactureYear2) {
        return bikeRepository.findAllByManufactureYearBetween(manufactureYear, manufactureYear2);
    }

}
