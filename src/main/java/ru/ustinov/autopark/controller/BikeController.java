package ru.ustinov.autopark.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ustinov.autopark.dto.BikeDTO;
import ru.ustinov.autopark.model.Engine;
import ru.ustinov.autopark.service.BikeService;
import ru.ustinov.autopark.util.*;

import java.util.List;

@RestController
@RequestMapping("/bikes")
public class BikeController {
    private final BikeService bikeService;
    @Autowired
    public BikeController(BikeService bikeService) {
        this.bikeService = bikeService;
    }
    @GetMapping("/id/{id}")
    public  ResponseEntity<BikeDTO> getBikeById(@PathVariable("id") int id) {
        return new ResponseEntity<>(bikeService.findDTOById(id), HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<List<BikeDTO>> getAllBikes() {
        return new ResponseEntity<>(bikeService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/count")
    public ResponseEntity<Integer> countAllBikes() {
        return new ResponseEntity<>(bikeService.countAllBy(), HttpStatus.OK);
    }
    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<BikeDTO>> getAllBikesByBrand(@PathVariable("brand") String brand) {
        return new ResponseEntity<>(bikeService.findAllByBrand(brand), HttpStatus.OK);
    }
    @GetMapping("/brand/{brand}/count")
    public ResponseEntity<Integer> countAllBikesByBrand(@PathVariable("brand") String brand) {
        return new ResponseEntity<>(bikeService.countAllByBrand(brand), HttpStatus.OK);
    }
    @GetMapping("/engine/{engine}")
    public ResponseEntity<List<BikeDTO>> getAllBikesByEngine(@PathVariable("engine") String engine) {
        return new ResponseEntity<>(bikeService.findAllByEngine(engine), HttpStatus.OK);
    }
    @GetMapping("/engine/{engine}/count")
    public ResponseEntity<Integer> countAllBikesByEngine(@PathVariable("engine") String engine) {
        return new ResponseEntity<>(bikeService.countAllByEngine(engine), HttpStatus.OK);
    }
    @GetMapping("/year/{year}")
    public ResponseEntity<List<BikeDTO>> getAllBikesByYear(@PathVariable("year") Integer year) {
        return new ResponseEntity<>(bikeService.findAllByManufactureYear(year), HttpStatus.OK);
    }
    @GetMapping("/year/{year}/count")
    public ResponseEntity<Integer> countAllBikesByYear(@PathVariable("year") Integer year) {
        return new ResponseEntity<>(bikeService.countAllByManufactureYear(year), HttpStatus.OK);
    }
    @GetMapping("/year/before/{year}")
    public ResponseEntity<List<BikeDTO>> getAllBikesByYearBefore(@PathVariable("year") Integer year) {
        return new ResponseEntity<>(bikeService.findAllByManufactureYearLessThan(year), HttpStatus.OK);
    }
    @GetMapping("/year/before/{year}/count")
    public ResponseEntity<Integer> countAllBikesByYearBefore(@PathVariable("year") Integer year) {
        return new ResponseEntity<>(bikeService.countAllByManufactureYearLessThan(year), HttpStatus.OK);
    }
    @GetMapping("/year/after/{year}")
    public ResponseEntity<List<BikeDTO>> getAllBikesByYearAfter(@PathVariable("year") Integer year) {
        return new ResponseEntity<>(bikeService.findAllByManufactureYearGreaterThan(year), HttpStatus.OK);
    }
    @GetMapping("/year/after/{year}/count")
    public ResponseEntity<Integer> countAllBikesByYearAfter(@PathVariable("year") Integer year) {
        return new ResponseEntity<>(bikeService.countAllByManufactureYearGreaterThan(year), HttpStatus.OK);
    }
    @GetMapping("/year/{year1}/{year2}")
    public ResponseEntity<List<BikeDTO>> getAllBikesByYearBetween(@PathVariable("year1") Integer year1,
                                                                @PathVariable("year2") Integer year2) {
        return new ResponseEntity<>(bikeService.findAllByManufactureYearBetween(year1, year2), HttpStatus.OK);
    }
    @GetMapping("/year/{year1}/{year2}/count")
    public ResponseEntity<Integer> countAllBikesByYearBetween(@PathVariable("year1") Integer year1,
                                                             @PathVariable("year2") Integer year2) {
        return new ResponseEntity<>(bikeService.countAllByManufactureYearBetween(year1, year2), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<HttpStatus> createBike(@RequestBody @Valid BikeDTO bikeDTO,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BikeNotCreatedException(bindingResult.getFieldErrors().toString());
        }
        bikeService.save(bikeDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @PatchMapping("/id/{id}")
    public ResponseEntity<HttpStatus> updateEngineBikeById(@PathVariable("id") int id,
                                             @RequestBody @Valid Engine newEngine,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BikeNotUpdatedException(bindingResult.getFieldErrors().toString());
        }
        bikeService.updateEngineById(id, newEngine);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @DeleteMapping("/id/{id}")
    public ResponseEntity<HttpStatus> deleteBikeById(@PathVariable("id") int id) {
        bikeService.deleteBikeById(id);
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
