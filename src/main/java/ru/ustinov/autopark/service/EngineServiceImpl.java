package ru.ustinov.autopark.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ustinov.autopark.model.Engine;
import ru.ustinov.autopark.repository.EngineRepository;
import ru.ustinov.autopark.util.EngineNotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
@Transactional(readOnly = true)
public class EngineServiceImpl implements EngineService {
    private final EngineRepository engineRepository;
    public EngineServiceImpl(EngineRepository engineRepository) {
        this.engineRepository = engineRepository;
    }
    @Override
    public Engine findById(Integer id) {
        log.info("Engine findById started");
        Optional<Engine> foundEngine = engineRepository.findById(id);
        log.info("Engine findById={}", foundEngine);
        log.info("Engine findById ended");
        return foundEngine.orElseThrow(() -> new EngineNotFoundException("Engine not found by id=" + id));
    }
    @Override
    public Engine findByEngine(String engine) {
        log.info("Engine findByEngine started");
        Optional<Engine> foundEngine = engineRepository.findByEngine(engine);
        log.info("Engine foundEngine={}", foundEngine);
        log.info("Engine findByEngine ended");
        return foundEngine.orElseThrow(() -> new EngineNotFoundException("Engine not found by engine=" + engine));
    }
    @Override
    public List<Engine> findAll() {
        log.info("Engine findAll started");
        log.info("Engine findAll ended");
        return engineRepository.findAll();
    }
    @Override
    public Set<String> findAllCarsAndBikesById(Integer id) {
        log.info("Engine findAllCarsAndBikesById started");
        Set<String> set = new HashSet<>();
        this.findById(id).getCars().forEach(car -> set.add(car.toString()));
        this.findById(id).getBikes().forEach(bike -> set.add(bike.toString()));
        log.info("Engine findAllCarsAndBikesById ended");
        return set;
    }
    @Override
    public Set<String> findAllCarsByEngine(String engine) {
        log.info("Engine findAllCarsByEngine started");
        Set<String> setCars = new HashSet<>();
        this.findByEngine(engine).getCars().forEach(car -> setCars.add(car.toString()));
        log.info("Engine findAllCarsByEngine ended");
        return setCars;
    }
    @Override
    public Integer countAllCarsByEngine(String engine) {
        log.info("Engine countAllCarsByEngine started");
        log.info("Engine countAllCarsByEngine ended");
        return this.findAllCarsByEngine(engine).size();
    }
    @Override
    public Set<String> findAllBikesByEngine(String engine) {
        log.info("Engine findAllBikesByEngine started");
        Set<String> setBikes = new HashSet<>();
        this.findByEngine(engine).getBikes().forEach(bike -> setBikes.add(bike.toString()));
        log.info("Engine findAllBikesByEngine ended");
        return setBikes;
    }
    @Override
    public Integer countAllBikesByEngine(String engine) {
        log.info("Engine countAllBikesByEngine started");
        log.info("Engine countAllBikesByEngine ended");
        return this.findAllBikesByEngine(engine).size();
    }
    @Override
    public Set<String> findAllCarsAndBikesByEngine(String engine) {
        log.info("Engine findAllCarsAndBikesByEngine started");
        Set<String> set = this.findAllCarsByEngine(engine);
        set.addAll(this.findAllBikesByEngine(engine));
        log.info("Engine findAllCarsAndBikesByEngine ended");
        return set;
    }
    @Override
    public Integer countAllCarsAndBikesByEngine(String engine) {
        log.info("Engine countAllCarsAndBikesByEngine started");
        log.info("Engine countAllCarsAndBikesByEngine ended");
        return this.findAllCarsAndBikesByEngine(engine).size();
    }
}
