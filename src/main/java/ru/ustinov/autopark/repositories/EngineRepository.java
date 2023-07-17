package ru.ustinov.autopark.repositories;

import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.ustinov.autopark.entities.Engine;

import java.util.Optional;

public interface EngineRepository extends JpaRepository<Engine, Integer> {
    @Query("select e from Engine e where e.engine = ?1")
    Optional<Engine> findByEngine(@NotNull String engine);

    @Query("select e from Engine e where e.id = ?1")
    @NonNull
    Optional<Engine> findById(@NonNull Integer id);

}