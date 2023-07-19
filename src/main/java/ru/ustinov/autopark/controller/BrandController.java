package ru.ustinov.autopark.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ustinov.autopark.model.Brand;
import ru.ustinov.autopark.service.BrandService;
import ru.ustinov.autopark.util.AutoparkException;
import ru.ustinov.autopark.util.BrandNotCreatedException;
import ru.ustinov.autopark.util.ErrorResponse;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/brands")
public class BrandController {
    private final BrandService brandService;
    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }
    @GetMapping
    public ResponseEntity<Set<String>> getAllBrands() {
        return new ResponseEntity<>(brandService.findAll()
                .stream()
                .map(Brand::getBrand)
                .collect(Collectors.toSet()), HttpStatus.OK);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Set<String>> getAllCarsAndBikesById(@PathVariable("id") int id) {
        return new ResponseEntity<>(brandService.findAllCarsAndBikesById(id), HttpStatus.OK);
    }
    @GetMapping("/{brand}")
    public ResponseEntity<Set<String>> getAllCarsAndBikesByBrand(@PathVariable("brand") String brand) {
        return new ResponseEntity<>(brandService.findAllCarsAndBikesByBrand(brand), HttpStatus.OK);
    }
    @GetMapping("/{brand}/count")
    public ResponseEntity<Integer> countAllCarsAndBikesByBrand(@PathVariable("brand") String brand) {
        return new ResponseEntity<>(brandService.countAllCarsAndBikesByBrand(brand), HttpStatus.OK);
    }
    @GetMapping("/{brand}/cars")
    public ResponseEntity<Set<String>> getAllCarsByBrand(@PathVariable("brand") String brand) {
        return new ResponseEntity<>(brandService.findAllCarsByBrand(brand), HttpStatus.OK);
    }
    @GetMapping("/{brand}/cars/count")
    public ResponseEntity<Integer> countAllCarsByBrand(@PathVariable("brand") String brand) {
        return new ResponseEntity<>(brandService.countAllCarsByBrand(brand), HttpStatus.OK);
    }
    @GetMapping("/{brand}/bikes")
    public ResponseEntity<Set<String>> getAllBikesByBrand(@PathVariable("brand") String brand) {
        return new ResponseEntity<>(brandService.findAllBikesByBrand(brand), HttpStatus.OK);
    }
    @GetMapping("/{brand}/bikes/count")
    public ResponseEntity<Integer> countAllBikesByBrand(@PathVariable("brand") String brand) {
        return new ResponseEntity<>(brandService.countAllBikesByBrand(brand), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<HttpStatus> createBrand(@RequestBody @Valid Brand brand,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BrandNotCreatedException(bindingResult.getFieldErrors().toString());
        }
        brandService.save(brand);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @PatchMapping("/{brand}")
    public ResponseEntity<HttpStatus> updateBrandByBrand(@PathVariable("brand") String oldBrand,
                                                         @RequestBody @Valid Brand newBrand,
                                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BrandNotCreatedException(bindingResult.getFieldErrors().toString());
        }
        brandService.updateBrandByBrand(oldBrand, newBrand);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @PatchMapping("/id/{id}")
    public ResponseEntity<HttpStatus> updateBrandById(@PathVariable("id") Integer id,
                                                         @RequestBody @Valid Brand newBrand,
                                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BrandNotCreatedException(bindingResult.getFieldErrors().toString());
        }
        brandService.updateBrandById(id, newBrand);
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
