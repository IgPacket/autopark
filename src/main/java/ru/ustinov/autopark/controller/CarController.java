package ru.ustinov.autopark.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ustinov.autopark.dto.CarDTO;
import ru.ustinov.autopark.model.Engine;
import ru.ustinov.autopark.service.CarService;
import ru.ustinov.autopark.util.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;
    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<CarDTO> getCarById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(carService.findDTOById(id), HttpStatus.OK);
    }
    @GetMapping("/{vinCode}")
    public ResponseEntity<CarDTO> getCarByVinCode(@PathVariable("vinCode") String vinCode) {
        return new ResponseEntity<>(carService.findDTOByVinCode(vinCode), HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<List<CarDTO>> getAllCars() {
        return new ResponseEntity<>(carService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/count")
    public ResponseEntity<Integer> countAllCars() {
        return new ResponseEntity<>(carService.countAllBy(), HttpStatus.OK);
    }
    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<CarDTO>> getAllCarsByBrand(@PathVariable("brand") String brand) {
        return new ResponseEntity<>(carService.findAllByBrand(brand), HttpStatus.OK);
    }
    @GetMapping("/brand/{brand}/count")
    public ResponseEntity<Integer> countAllCarsByBrand(@PathVariable("brand") String brand) {
        return new ResponseEntity<>(carService.countAllByBrand(brand), HttpStatus.OK);
    }
    @GetMapping("/engine/{engine}")
    public ResponseEntity<List<CarDTO>> getAllCarsByEngine(@PathVariable("engine") String engine) {
        return new ResponseEntity<>(carService.findAllByEngine(engine), HttpStatus.OK);
    }
    @GetMapping("/engine/{engine}/count")
    public ResponseEntity<Integer> countAllCarsByEngine(@PathVariable("engine") String engine) {
        return new ResponseEntity<>(carService.countAllByEngine(engine), HttpStatus.OK);
    }
    @GetMapping("/year/{year}")
    public ResponseEntity<List<CarDTO>> getAllCarsByYear(@PathVariable("year") Integer year) {
        return new ResponseEntity<>(carService.findAllByManufactureYear(year), HttpStatus.OK);
    }
    @GetMapping("/year/{year}/count")
    public ResponseEntity<Integer> countAllCarsByYear(@PathVariable("year") Integer year) {
        return new ResponseEntity<>(carService.countAllByManufactureYear(year), HttpStatus.OK);
    }
    @GetMapping("/year/before/{year}")
    public ResponseEntity<List<CarDTO>> getAllCarsByYearBefore(@PathVariable("year") Integer year) {
        return new ResponseEntity<>(carService.findAllByManufactureYearLessThan(year), HttpStatus.OK);
    }
    @GetMapping("/year/before/{year}/count")
    public ResponseEntity<Integer> countAllCarsByYearBefore(@PathVariable("year") Integer year) {
        return new ResponseEntity<>(carService.countAllByManufactureYearLessThan(year), HttpStatus.OK);
    }
    @GetMapping("/year/after/{year}")
    public ResponseEntity<List<CarDTO>> getAllCarsByYearAfter(@PathVariable("year") Integer year) {
        return new ResponseEntity<>(carService.findAllByManufactureYearGreaterThan(year), HttpStatus.OK);
    }
    @GetMapping("/year/after/{year}/count")
    public ResponseEntity<Integer> countAllCarsByYearAfter(@PathVariable("year") Integer year) {
        return new ResponseEntity<>(carService.countAllByManufactureYearGreaterThan(year), HttpStatus.OK);
    }
    @GetMapping("/year/{year1}/{year2}")
    public ResponseEntity<List<CarDTO>> getAllCarsByYearBetween(@PathVariable("year1") Integer year1,
                                                                @PathVariable("year2") Integer year2) {
        return new ResponseEntity<>(carService.findAllByManufactureYearBetween(year1, year2), HttpStatus.OK);
    }
    @GetMapping("/year/{year1}/{year2}/count")
    public ResponseEntity<Integer> countAllCarsByYearBetween(@PathVariable("year1") Integer year1,
                                                                @PathVariable("year2") Integer year2) {
        return new ResponseEntity<>(carService.countAllByManufactureYearBetween(year1, year2), HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<HttpStatus> createCar(@RequestBody @Valid CarDTO carDTO,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new CarNotCreatedException(bindingResult.getFieldErrors().toString());
        }
        carService.save(carDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @PatchMapping("/id/{id}")
    public ResponseEntity<HttpStatus> updateEngineCarById(@PathVariable("id") Integer id,
                                             @RequestBody @Valid Engine newEngine,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new CarNotUpdatedException(bindingResult.getFieldErrors().toString());
        }
        carService.updateEngineById(id, newEngine);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @PatchMapping("/{vinCode}")
    public ResponseEntity<HttpStatus> updateEngineCarByVinCode(@PathVariable("vinCode") String vinCode,
                                                               @RequestBody @Valid Engine newEngine,
                                                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new CarNotUpdatedException(bindingResult.getFieldErrors().toString());
        }
        carService.updateEngineByVinCode(vinCode, newEngine);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @DeleteMapping("/id/{id}")
    public ResponseEntity<HttpStatus> deleteCarById(@PathVariable("id") int id) {
        carService.deleteCarById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @DeleteMapping("/{vinCode}")
    public ResponseEntity<HttpStatus> deleteCarByVinCode(@PathVariable("vinCode") String vinCode) {
        carService.deleteCarByVinCode(vinCode);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(AutoparkException e) {
        ErrorResponse response = new ErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
