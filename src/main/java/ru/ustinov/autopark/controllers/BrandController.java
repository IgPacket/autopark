package ru.ustinov.autopark.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ustinov.autopark.entities.Brand;
import ru.ustinov.autopark.entities.Car;
import ru.ustinov.autopark.entities.Bike;
import ru.ustinov.autopark.services.BrandService;

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
    @GetMapping()
    public Set<String> getBrands() {
        return brandService.findAll().stream().map(Brand::getBrand)
                .collect(Collectors.toSet());
    }
    @GetMapping("/{id}")
    public Set<String> getAutos(@PathVariable("id") int id) {
        Set<String> setCars = brandService.findById(id).getCars().stream()
                .map(Car::toString).collect(Collectors.toSet());

        Set<String> setBikes = brandService.findById(id).getBikes().stream()
                .map(Bike::toString).collect(Collectors.toSet());
        setCars.addAll(setBikes);
        return setCars;
    }
    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid Brand brand,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);

        brandService.save(brand);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
