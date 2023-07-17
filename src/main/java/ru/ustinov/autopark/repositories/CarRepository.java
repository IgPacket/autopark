package ru.ustinov.autopark.repositories;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.ustinov.autopark.entities.Brand;
import ru.ustinov.autopark.entities.Car;
import ru.ustinov.autopark.entities.Engine;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Integer> {
    @Query("select c from Car c where c.id = ?1")
    @NonNull
    Optional<Car> findById(@NonNull Integer id);

    @Query("select c from Car c")
    @NonNull
    List<Car> findAll();

    @Query("select c from Car c where c.brand = ?1")
    List<Car> findAllByBrand(Brand brand);

    @Query("select c from Car c where c.engine = ?1")
    List<Car> findAllByEngine(Engine engine);

    @Query("select c from Car c where c.manufactureYear = ?1")
    List<Car> findAllByManufactureYear(@NotNull Integer manufactureYear);

    @Query("select c from Car c where c.manufactureYear > ?1")
    List<Car> findAllByManufactureYearGreaterThan(@NotNull Integer manufactureYear);

    @Query("select c from Car c where c.manufactureYear < ?1")
    List<Car> findAllByManufactureYearLessThan(@NotNull Integer manufactureYear);

    @Query("select c from Car c where c.manufactureYear between ?1 and ?2")
    List<Car> findAllByManufactureYearBetween(@NotNull Integer manufactureYear, @NotNull Integer manufactureYear2);

    @Query("select c from Car c where c.vinCode = ?1")
    List<Car> findAllByVinCode(@Size(max = 17) @NotNull String vinCode);

    @Modifying
    @Query("update Car c set c.engine = ?2 where c.id = ?1")
    void updateEngine(Integer id, Engine engine);
}