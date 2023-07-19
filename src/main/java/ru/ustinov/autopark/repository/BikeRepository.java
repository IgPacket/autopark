package ru.ustinov.autopark.repository;

import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.ustinov.autopark.model.Bike;
import ru.ustinov.autopark.model.Brand;
import ru.ustinov.autopark.model.Engine;

import java.util.List;
import java.util.Optional;

public interface BikeRepository extends JpaRepository<Bike, Integer> {
    @Query("select b from Bike b where b.id = ?1")
    @NonNull
    Optional<Bike> findById(@NonNull Integer id);
    @Query("select b from Bike b")
    @NonNull
    List<Bike> findAll();
    @Query("select count(b) from Bike b")
    Integer countAllBy();
    @Query("select b from Bike b where b.brand = ?1")
    List<Bike> findAllByBrand(Brand brand);
    @Query("select count(b) from Bike b where b.brand = ?1")
    Integer countAllByBrand(Brand brand);
    @Query("select b from Bike b where b.engine = ?1")
    List<Bike> findAllByEngine(Engine engine);
    @Query("select count(b) from Bike b where b.engine = ?1")
    Integer countAllByEngine(Engine engine);
    @Query("select b from Bike b where b.manufactureYear = ?1")
    List<Bike> findAllByManufactureYear(@NotNull Integer manufactureYear);
    @Query("select count(b) from Bike b where b.manufactureYear = ?1")
    Integer countAllByManufactureYear(@NotNull Integer manufactureYear);
    @Query("select b from Bike b where b.manufactureYear < ?1")
    List<Bike> findAllByManufactureYearLessThan(@NotNull Integer manufactureYear);
    @Query("select count(b) from Bike b where b.manufactureYear < ?1")
    Integer countAllByManufactureYearLessThan(@NotNull Integer manufactureYear);
    @Query("select b from Bike b where b.manufactureYear > ?1")
    List<Bike> findAllByManufactureYearGreaterThan(@NotNull Integer manufactureYear);
    @Query("select count(b) from Bike b where b.manufactureYear > ?1")
    Integer countAllByManufactureYearGreaterThan(@NotNull Integer manufactureYear);
    @Query("select b from Bike b where b.manufactureYear between ?1 and ?2")
    List<Bike> findAllByManufactureYearBetween(@NotNull Integer manufactureYear, @NotNull Integer manufactureYear2);
    @Query("select count(b) from Bike b where b.manufactureYear between ?1 and ?2")
    Integer countAllByManufactureYearBetween(@NotNull Integer manufactureYear, @NotNull Integer manufactureYear2);
}