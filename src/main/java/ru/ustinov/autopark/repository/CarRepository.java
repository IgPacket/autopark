package ru.ustinov.autopark.repository;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.ustinov.autopark.model.Brand;
import ru.ustinov.autopark.model.Car;
import ru.ustinov.autopark.model.Engine;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Integer> {
    @Query("select c from Car c where c.id = ?1")
    @NonNull
    Optional<Car> findById(@NonNull Integer id);
    @Query("select c from Car c where c.vinCode = ?1")
    Optional<Car> findByVinCode(@Size(max = 17) @NotNull String vinCode);
    @Query("select c from Car c")
    @NonNull
    List<Car> findAll();
    @Query("select count(c) from Car c")
    Integer countAllBy();
    @Query("select c from Car c where c.brand = ?1")
    List<Car> findAllByBrand(Brand brand);
    @Query("select count(c) from Car c where c.brand = ?1")
    Integer countAllByBrand(Brand brand);
    @Query("select c from Car c where c.engine = ?1")
    List<Car> findAllByEngine(Engine engine);
    @Query("select count(c) from Car c where c.engine = ?1")
    Integer countAllByEngine(Engine engine);
    @Query("select c from Car c where c.manufactureYear = ?1")
    List<Car> findAllByManufactureYear(@NotNull Integer manufactureYear);
    @Query("select count(c) from Car c where c.manufactureYear = ?1")
    Integer countAllByManufactureYear(@NotNull Integer manufactureYear);
    @Query("select c from Car c where c.manufactureYear < ?1")
    List<Car> findAllByManufactureYearLessThan(@NotNull Integer manufactureYear);
    @Query("select count(c) from Car c where c.manufactureYear < ?1")
    Integer countAllByManufactureYearLessThan(@NotNull Integer manufactureYear);
    @Query("select c from Car c where c.manufactureYear > ?1")
    List<Car> findAllByManufactureYearGreaterThan(@NotNull Integer manufactureYear);
    @Query("select count(c) from Car c where c.manufactureYear > ?1")
    Integer countAllByManufactureYearGreaterThan(@NotNull Integer manufactureYear);
    @Query("select c from Car c where c.manufactureYear between ?1 and ?2")
    List<Car> findAllByManufactureYearBetween(@NotNull Integer manufactureYear, @NotNull Integer manufactureYear2);
    @Query("select count(c) from Car c where c.manufactureYear between ?1 and ?2")
    Integer countAllByManufactureYearBetween(@NotNull Integer manufactureYear, @NotNull Integer manufactureYear2);
}