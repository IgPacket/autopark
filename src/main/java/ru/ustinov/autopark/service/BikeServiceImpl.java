package ru.ustinov.autopark.service;

import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ustinov.autopark.dto.BikeDTO;
import ru.ustinov.autopark.mapper.BikeMapper;
import ru.ustinov.autopark.model.Bike;
import ru.ustinov.autopark.model.Brand;
import ru.ustinov.autopark.model.Engine;
import ru.ustinov.autopark.repository.BikeRepository;
import ru.ustinov.autopark.util.BikeNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional(readOnly = true)
public class BikeServiceImpl implements BikeService {
    private final BikeRepository bikeRepository;
    private final BrandService brandService;
    private final EngineService engineService;
    private final BikeMapper bikeMapper;
    public BikeServiceImpl(BikeRepository bikeRepository,
                           BrandService brandService,
                           EngineService engineService,
                           BikeMapper bikeMapper) {
        this.bikeRepository = bikeRepository;
        this.brandService = brandService;
        this.engineService = engineService;
        this.bikeMapper = bikeMapper;
    }
    @Override
    public Bike findById(Integer id) {
        log.info("Bike findById started");
        Optional<Bike> foundBike = bikeRepository.findById(id);
        log.info("Bike foundBike={}", foundBike);
        log.info("Bike findById ended");
        return foundBike.orElseThrow(() -> new BikeNotFoundException("Bike not found by id=" + id));
    }
    @Override
    public BikeDTO findDTOById(Integer id) {
        log.info("Bike findDTOById started");
        Bike foundBike = this.findById(id);
        log.info("Bike findDTOById ended");
        return bikeMapper.toDto(foundBike);
    }
    @Override
    public List<BikeDTO> findAll() {
        log.info("Bike findAll started");
        log.info("Bike findAll ended");
        return bikeRepository.findAll()
                .stream()
                .map(bikeMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public Integer countAllBy() {
        log.info("Bike countAllBy started");
        log.info("Bike countAllBy ended");
        return bikeRepository.countAllBy();
    }
    @Override
    public List<BikeDTO> findAllByBrand(String brand) {
        log.info("Bike findAllByBrand started");
        Brand foundBrand = brandService.findByBrand(brand);
        log.info("Bike findAllByBrand ended");
        return bikeRepository.findAllByBrand(foundBrand)
                .stream()
                .map(bikeMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public Integer countAllByBrand(String brand) {
        log.info("Bike countAllByBrand started");
        Brand foundBrand = brandService.findByBrand(brand);
        log.info("Bike countAllByBrand ended");
        return bikeRepository.countAllByBrand(foundBrand);
    }
    @Override
    public List<BikeDTO> findAllByEngine(String engine) {
        log.info("Bike findAllByEngine started");
        Engine foundEngine = engineService.findByEngine(engine);
        log.info("Bike findAllByEngine ended");
        return bikeRepository.findAllByEngine(foundEngine)
                .stream()
                .map(bikeMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public Integer countAllByEngine(String engine) {
        log.info("Bike countAllByEngine started");
        Engine foundEngine = engineService.findByEngine(engine);
        log.info("Bike countAllByEngine ended");
        return bikeRepository.countAllByEngine(foundEngine);
    }
    @Override
    public List<BikeDTO> findAllByManufactureYear(@NotNull Integer manufactureYear) {
        log.info("Bike findAllByManufactureYear started");
        log.info("Bike findAllByManufactureYear ended");
        return bikeRepository.findAllByManufactureYear(manufactureYear)
                .stream()
                .map(bikeMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public Integer countAllByManufactureYear(Integer manufactureYear) {
        log.info("Bike countAllByManufactureYear started");
        log.info("Bike countAllByManufactureYear ended");
        return bikeRepository.countAllByManufactureYear(manufactureYear);
    }
    @Override
    public List<BikeDTO> findAllByManufactureYearLessThan(@NotNull Integer manufactureYear) {
        log.info("Bike findAllByManufactureYearLessThan started");
        log.info("Bike findAllByManufactureYearLessThan ended");
        return bikeRepository.findAllByManufactureYearLessThan(manufactureYear)
                .stream()
                .map(bikeMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public Integer countAllByManufactureYearLessThan(Integer manufactureYear) {
        log.info("Bike countAllByManufactureYearLessThan started");
        log.info("Bike countAllByManufactureYearLessThan ended");
        return bikeRepository.countAllByManufactureYearLessThan(manufactureYear);
    }
    @Override
    public List<BikeDTO> findAllByManufactureYearGreaterThan(@NotNull Integer manufactureYear) {
        log.info("Bike findAllByManufactureYearGreaterThan started");
        log.info("Bike findAllByManufactureYearGreaterThan ended");
        return bikeRepository.findAllByManufactureYearGreaterThan(manufactureYear)
                .stream()
                .map(bikeMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public Integer countAllByManufactureYearGreaterThan(Integer manufactureYear) {
        log.info("Bike countAllByManufactureYearGreaterThan started");
        log.info("Bike countAllByManufactureYearGreaterThan ended");
        return bikeRepository.countAllByManufactureYearGreaterThan(manufactureYear);
    }
    @Override
    public List<BikeDTO> findAllByManufactureYearBetween(@NotNull Integer manufactureYear,
                                                         @NotNull Integer manufactureYear2) {
        log.info("Bike findAllByManufactureYearBetween started");
        log.info("Bike findAllByManufactureYearBetween ended");
        return bikeRepository.findAllByManufactureYearBetween(manufactureYear, manufactureYear2)
                .stream()
                .map(bikeMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public Integer countAllByManufactureYearBetween(Integer manufactureYear, Integer manufactureYear2) {
        log.info("Bike countAllByManufactureYearBetween started");
        log.info("Bike countAllByManufactureYearBetween ended");
        return bikeRepository.countAllByManufactureYearBetween(manufactureYear, manufactureYear2);
    }
    @Override
    @Transactional
    public void save(BikeDTO bikeDTO) {
        log.info("Bike save started");
        Brand brand = brandService.findByBrand(bikeDTO.getBrand());
        Engine engine = engineService.findByEngine(bikeDTO.getEngine());
        bikeRepository.save(bikeMapper.toEntity(bikeDTO, brand, engine));
        log.info("Bike save ended");
    }
    @Override
    @Transactional
    public void updateEngineById(Integer id, Engine newEngine) {
        log.info("Bike updateEngineById started");
        Bike updatedBike = this.findById(id);
        Engine engine = engineService.findByEngine(newEngine.getEngine());
        updatedBike.setEngine(engine);
        bikeRepository.save(updatedBike);
        log.info("Bike updateEngineById ended");
    }
    @Override
    @Transactional
    public void deleteBikeById(Integer id) {
        log.info("Bike deleteBikeById started");
        Bike bike = this.findById(id);
        bikeRepository.delete(bike);
        log.info("Bike deleteBikeById ended");
    }
}
