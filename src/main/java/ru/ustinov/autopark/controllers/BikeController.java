package ru.ustinov.autopark.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ustinov.autopark.dto.BikeDTO;
import ru.ustinov.autopark.entities.Brand;
import ru.ustinov.autopark.entities.Bike;
import ru.ustinov.autopark.entities.Engine;
import ru.ustinov.autopark.services.BikeService;
import ru.ustinov.autopark.services.BrandService;
import ru.ustinov.autopark.services.EngineService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bikes")
public class BikeController {
    private final BikeService bikeService;
    private final BrandService brandService;
    private final EngineService engineService;
    private final ModelMapper modelMapper;

    @Autowired
    public BikeController(BikeService bikeService,
                          BrandService brandService,
                          EngineService engineService,
                          ModelMapper modelMapper) {
        this.bikeService = bikeService;
        this.brandService = brandService;
        this.engineService = engineService;
        this.modelMapper = modelMapper;
    }
    private Bike convertToBike(BikeDTO bikeDTO) {
        Brand brand = brandService.findByBrand(bikeDTO.getBrand());
        Engine engine = engineService.findByEngine(bikeDTO.getEngine());
        return Bike.builder()
                .brand(brand)
                .engine(engine)
                .manufactureYear(bikeDTO.getManufactureYear())
                .build();
        //return modelMapper.map(carDTO, Car.class);
    }
    private BikeDTO convertToBikeDTO(Bike bike) {
        return modelMapper.map(bike, BikeDTO.class);
    }
    @GetMapping()
    public List<BikeDTO> getBikes() {
        return bikeService.findAll().stream().map(this::convertToBikeDTO)
                .collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public BikeDTO getBike(@PathVariable("id") int id) {
        return convertToBikeDTO(bikeService.findById(id));
    }
    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid BikeDTO bikeDTO,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);

        bikeService.save(convertToBike(bikeDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable("id") int id,
                                             @RequestBody @Valid Engine newEngine,
                                             BindingResult bindingResult) {

        if (bindingResult.hasErrors() || bikeService.findById(id) == null)
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);

        Engine engine = engineService.findByEngine(newEngine.getEngine());
        bikeService.updateEngine(id, engine);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        if (bikeService.findById(id) == null)
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);

        Bike bike = bikeService.findById(id);
        bikeService.delete(bike);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
