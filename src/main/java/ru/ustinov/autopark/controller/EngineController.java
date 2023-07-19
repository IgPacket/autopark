package ru.ustinov.autopark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ustinov.autopark.model.Engine;
import ru.ustinov.autopark.service.EngineService;
import ru.ustinov.autopark.util.AutoparkException;
import ru.ustinov.autopark.util.ErrorResponse;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/engines")
public class EngineController {
    private final EngineService engineService;
    @Autowired
    public EngineController(EngineService engineService) {
        this.engineService = engineService;
    }
    @GetMapping()
    public ResponseEntity<Set<String>> getAllEngines() {
        return new ResponseEntity<>(engineService.findAll()
                .stream()
                .map(Engine::getEngine)
                .collect(Collectors.toSet()), HttpStatus.OK);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Set<String>> getAllCarsAndBikesById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(engineService.findAllCarsAndBikesById(id), HttpStatus.OK);
    }
    @GetMapping("/{engine}")
    public ResponseEntity<Set<String>> getAllCarsAndBikesByEngine(@PathVariable("engine") String engine) {
        return new ResponseEntity<>(engineService.findAllCarsAndBikesByEngine(engine), HttpStatus.OK);
    }
    @GetMapping("/{engine}/count")
    public ResponseEntity<Integer> countAllCarsAndBikesByEngine(@PathVariable("engine") String brand) {
        return new ResponseEntity<>(engineService.countAllCarsAndBikesByEngine(brand), HttpStatus.OK);
    }
    @GetMapping("/{engine}/cars")
    public ResponseEntity<Set<String>> getAllCarsByEngine(@PathVariable("engine") String brand) {
        return new ResponseEntity<>(engineService.findAllCarsByEngine(brand), HttpStatus.OK);
    }
    @GetMapping("/{engine}/cars/count")
    public ResponseEntity<Integer> countAllCarsByEngine(@PathVariable("engine") String brand) {
        return new ResponseEntity<>(engineService.countAllCarsByEngine(brand), HttpStatus.OK);
    }
    @GetMapping("/{engine}/bikes")
    public ResponseEntity<Set<String>> getAllBikesByEngine(@PathVariable("engine") String brand) {
        return new ResponseEntity<>(engineService.findAllBikesByEngine(brand), HttpStatus.OK);
    }
    @GetMapping("/{engine}/bikes/count")
    public ResponseEntity<Integer> countAllBikesByEngine(@PathVariable("engine") String brand) {
        return new ResponseEntity<>(engineService.countAllBikesByEngine(brand), HttpStatus.OK);
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
