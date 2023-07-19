package ru.ustinov.autopark.service;

import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ustinov.autopark.dto.CarDTO;
import ru.ustinov.autopark.mapper.CarMapper;
import ru.ustinov.autopark.model.Brand;
import ru.ustinov.autopark.model.Car;
import ru.ustinov.autopark.model.Engine;
import ru.ustinov.autopark.repository.CarRepository;
import ru.ustinov.autopark.util.CarNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional(readOnly = true)
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final BrandService brandService;
    private final EngineService engineService;
    private final CarMapper carMapper;
    public CarServiceImpl(CarRepository carRepository,
                          BrandService brandService,
                          EngineService engineService,
                          CarMapper carMapper) {
        this.carRepository = carRepository;
        this.brandService = brandService;
        this.engineService = engineService;
        this.carMapper = carMapper;
    }
    @Override
    public Car findById(int id) {
        log.info("Car findById started");
        Optional<Car> foundCar = carRepository.findById(id);
        log.info("Car foundCar={}", foundCar);
        log.info("Car findById ended");
        return foundCar.orElseThrow(() -> new CarNotFoundException("Car not found by id=" + id));
    }
    @Override
    public CarDTO findDTOById(int id) {
        log.info("Car findDTOById started");
        Car foundCar = this.findById(id);
        log.info("Car findDTOById ended");
        return carMapper.toDto(foundCar);
    }
    @Override
    public Car findByVinCode(String vinCode) {
        log.info("Car findByVinCode started");
        Optional<Car> foundCar = carRepository.findByVinCode(vinCode);
        log.info("Car foundCar={}", foundCar);
        log.info("Car findByVinCode ended");
        return foundCar.orElseThrow(() -> new CarNotFoundException("Car not found by vinCode=" + vinCode));
    }
    @Override
    public CarDTO findDTOByVinCode(String vinCode) {
        log.info("Car findDTOByVinCode started");
        Car foundCar = this.findByVinCode(vinCode);
        log.info("Car findDTOByVinCode ended");
        return carMapper.toDto(foundCar);
    }
    @Override
    public List<CarDTO> findAll() {
        log.info("Car findAll started");
        log.info("Car findAll ended");
        return carRepository.findAll()
                .stream()
                .map(carMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public Integer countAllBy() {
        log.info("Car countAllBy started");
        log.info("Car countAllBy ended");
        return carRepository.countAllBy();
    }
    @Override
    public List<CarDTO> findAllByBrand(String brand) {
        log.info("Car findAllByBrand started");
        Brand foundBrand = brandService.findByBrand(brand);
        log.info("Car findAllByBrand ended");
        return carRepository.findAllByBrand(foundBrand)
                .stream()
                .map(carMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public Integer countAllByBrand(String brand) {
        log.info("Car countAllByBrand started");
        Brand foundBrand = brandService.findByBrand(brand);
        log.info("Car countAllByBrand ended");
        return carRepository.countAllByBrand(foundBrand);
    }
    @Override
    public List<CarDTO> findAllByEngine(String engine) {
        log.info("Car findAllByEngine started");
        Engine foundEngine = engineService.findByEngine(engine);
        log.info("Car findAllByEngine ended");
        return carRepository.findAllByEngine(foundEngine)
                .stream()
                .map(carMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public Integer countAllByEngine(String engine) {
        log.info("Car countAllByEngine started");
        Engine foundEngine = engineService.findByEngine(engine);
        log.info("Car countAllByEngine ended");
        return carRepository.countAllByEngine(foundEngine);
    }
    @Override
    public List<CarDTO> findAllByManufactureYear(@NotNull Integer manufactureYear) {
        log.info("Car findAllByManufactureYear started");
        log.info("Car findAllByManufactureYear ended");
        return carRepository.findAllByManufactureYear(manufactureYear)
                .stream()
                .map(carMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public Integer countAllByManufactureYear(Integer manufactureYear) {
        log.info("Car countAllByManufactureYear started");
        log.info("Car countAllByManufactureYear ended");
        return carRepository.countAllByManufactureYear(manufactureYear);
    }
    @Override
    public List<CarDTO> findAllByManufactureYearLessThan(@NotNull Integer manufactureYear) {
        log.info("Car findAllByManufactureYearLessThan started");
        log.info("Car findAllByManufactureYearLessThan ended");
        return carRepository.findAllByManufactureYearLessThan(manufactureYear)
                .stream()
                .map(carMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public Integer countAllByManufactureYearLessThan(Integer manufactureYear) {
        log.info("Car countAllByManufactureYearLessThan started");
        log.info("Car countAllByManufactureYearLessThan ended");
        return carRepository.countAllByManufactureYearLessThan(manufactureYear);
    }
    @Override
    public List<CarDTO> findAllByManufactureYearGreaterThan(@NotNull Integer manufactureYear) {
        log.info("Car findAllByManufactureYearGreaterThan started");
        log.info("Car findAllByManufactureYearGreaterThan ended");
        return carRepository.findAllByManufactureYearGreaterThan(manufactureYear)
                .stream()
                .map(carMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public Integer countAllByManufactureYearGreaterThan(Integer manufactureYear) {
        log.info("Car countAllByManufactureYearGreaterThan started");
        log.info("Car countAllByManufactureYearGreaterThan ended");
        return carRepository.countAllByManufactureYearGreaterThan(manufactureYear);
    }
    @Override
    public List<CarDTO> findAllByManufactureYearBetween(@NotNull Integer manufactureYear,
                                                     @NotNull Integer manufactureYear2) {
        log.info("Car findAllByManufactureYearBetween started");
        log.info("Car findAllByManufactureYearBetween ended");
        return carRepository.findAllByManufactureYearBetween(manufactureYear, manufactureYear2)
                .stream()
                .map(carMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public Integer countAllByManufactureYearBetween(Integer manufactureYear, Integer manufactureYear2) {
        log.info("Car countAllByManufactureYearBetween started");
        log.info("Car countAllByManufactureYearBetween ended");
        return carRepository.countAllByManufactureYearBetween(manufactureYear, manufactureYear2);
    }
    @Override
    @Transactional
    public void save(CarDTO carDTO) {
        log.info("Car save started");
        Brand brand = brandService.findByBrand(carDTO.getBrand());
        Engine engine = engineService.findByEngine(carDTO.getEngine());
        carRepository.save(carMapper.toEntity(carDTO, brand, engine));
        log.info("Car save ended");
    }
    @Override
    @Transactional
    public void updateEngine(Car updatedCar, Engine newEngine) {
        log.info("Car updateEngine started");
        Engine engine = engineService.findByEngine(newEngine.getEngine());
        updatedCar.setEngine(engine);
        carRepository.save(updatedCar);
        log.info("Car updateEngine ended");
    }
    @Override
    @Transactional
    public void updateEngineById(Integer id, Engine newEngine) {
        log.info("Car updateEngineById started");
        Car updatedCar = this.findById(id);
        updateEngine(updatedCar, newEngine);
        log.info("Car updateEngineById ended");
    }
    @Override
    @Transactional
    public void updateEngineByVinCode(String vinCode, Engine newEngine) {
        log.info("Car updateEngineByVinCode started");
        Car updatedCar = this.findByVinCode(vinCode);
        updateEngine(updatedCar, newEngine);
        log.info("Car updateEngineByVinCode ended");
    }
    @Override
    @Transactional
    public void deleteCarById(Integer id) {
        log.info("Car deleteCarById started");
        Car car = this.findById(id);
        carRepository.delete(car);
        log.info("Car deleteCarById ended");
    }
    @Override
    @Transactional
    public void deleteCarByVinCode(String vinCode) {
        log.info("Car deleteCarByVinCode started");
        Car car = this.findByVinCode(vinCode);
        carRepository.delete(car);
        log.info("Car deleteCarByVinCode ended");
    }
}
