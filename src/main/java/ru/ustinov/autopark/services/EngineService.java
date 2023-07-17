package ru.ustinov.autopark.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ustinov.autopark.entities.Engine;
import ru.ustinov.autopark.repositories.EngineRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class EngineService {
    private final EngineRepository engineRepository;
    public EngineService(EngineRepository engineRepository) {
        this.engineRepository = engineRepository;
    }
    public Engine findByEngine(String engine) {
        Optional<Engine> foundEngine = engineRepository.findByEngine(engine);
        return foundEngine.orElse(null);
    }
    public List<Engine> findAll() {
        return engineRepository.findAll();
    }

    public Engine findById(Integer id) {
        Optional<Engine> foundEngine = engineRepository.findById(id);
        return foundEngine.orElse(null);
    }
}
