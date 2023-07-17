package ru.ustinov.autopark.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.ustinov.autopark.dto.CarDTO;
import ru.ustinov.autopark.entities.Brand;
import ru.ustinov.autopark.entities.Car;
import ru.ustinov.autopark.entities.Engine;
import ru.ustinov.autopark.services.BrandService;
import ru.ustinov.autopark.services.CarService;
import ru.ustinov.autopark.services.EngineService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;
    private final BrandService brandService;
    private final EngineService engineService;
    private final ModelMapper modelMapper;

    @Autowired
    public CarController(CarService carService,
                         BrandService brandService,
                         EngineService engineService,
                         ModelMapper modelMapper) {
        this.carService = carService;
        this.brandService = brandService;
        this.engineService = engineService;
        this.modelMapper = modelMapper;
    }
    private Car convertToCar(CarDTO carDTO) {
        Brand brand = brandService.findByBrand(carDTO.getBrand());
        Engine engine = engineService.findByEngine(carDTO.getEngine());
        return Car.builder()
                .brand(brand)
                .engine(engine)
                .vinCode(carDTO.getVinCode())
                .manufactureYear(carDTO.getManufactureYear())
                .build();
        //return modelMapper.map(carDTO, Car.class);
    }

    private CarDTO convertToCarDTO(Car car) {
        return modelMapper.map(car, CarDTO.class);
    }
    @GetMapping()
    public List<CarDTO> getCars() {
        return carService.findAll().stream().map(this::convertToCarDTO)
                .collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public CarDTO getCar(@PathVariable("id") int id) {
        return convertToCarDTO(carService.findById(id));
    }
    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid CarDTO carDTO,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);

        carService.save(convertToCar(carDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable("id") int id,
                                             @RequestBody @Valid Engine newEngine,
                                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error: errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
            System.out.println(errorMsg);
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
        if (carService.findById(id) == null)  return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        Engine engine = engineService.findByEngine(newEngine.getEngine());
        carService.updateEngine(id, engine);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        Car car = carService.findById(id);
        if (carService.findById(id) == null)
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        carService.delete(car);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
