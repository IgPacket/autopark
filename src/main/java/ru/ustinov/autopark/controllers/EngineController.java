package ru.ustinov.autopark.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ustinov.autopark.entities.Engine;
import ru.ustinov.autopark.services.EngineService;

import java.util.HashSet;
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
    public Set<String> getEngines() {
        return engineService.findAll().stream().map(Engine::getEngine)
                .collect(Collectors.toSet());
    }
    @GetMapping("/{id}")
    public Set<String> getAutos(@PathVariable("id") int id) {
        Set<String> set = new HashSet<>();
        engineService.findById(id).getCars().forEach(car -> set.add(car.toString()));
        engineService.findById(id).getBikes().forEach(bike -> set.add(bike.toString()));
        return set;
    }
}
