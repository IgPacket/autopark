package ru.ustinov.autopark.service;

import ru.ustinov.autopark.model.Engine;

import java.util.List;
import java.util.Set;

public interface EngineService {
    Engine findById(Integer id);
    Engine findByEngine(String engine);
    List<Engine> findAll();
    Set<String> findAllCarsAndBikesById(Integer id);
    Set<String> findAllCarsByEngine(String engine);
    Integer countAllCarsByEngine(String engine);
    Set<String> findAllBikesByEngine(String engine);
    Integer countAllBikesByEngine(String engine);
    Set<String> findAllCarsAndBikesByEngine(String engine);
    Integer countAllCarsAndBikesByEngine(String engine);
}
