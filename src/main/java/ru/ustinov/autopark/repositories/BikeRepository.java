package ru.ustinov.autopark.repositories;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.ustinov.autopark.entities.Bike;
import ru.ustinov.autopark.entities.Brand;
import ru.ustinov.autopark.entities.Engine;

import java.util.List;

public interface BikeRepository extends JpaRepository<Bike, Integer> {
    @Query("select b from Bike b where b.brand = ?1")
    List<Bike> findAllByBrand(Brand brand);

    @Query("select b from Bike b where b.engine = ?1")
    List<Bike> findAllByEngine(Engine engine);

    @Query("select b from Bike b where b.manufactureYear = ?1")
    List<Bike> findAllByManufactureYear(@NotNull Integer manufactureYear);

    @Query("select b from Bike b where b.manufactureYear > ?1")
    List<Bike> findAllByManufactureYearGreaterThan(@NotNull Integer manufactureYear);

    @Query("select b from Bike b where b.manufactureYear < ?1")
    List<Bike> findAllByManufactureYearLessThan(@NotNull Integer manufactureYear);

    @Query("select b from Bike b where b.manufactureYear between ?1 and ?2")
    List<Bike> findAllByManufactureYearBetween(@NotNull Integer manufactureYear, @NotNull Integer manufactureYear2);

    @Modifying
    @Query("update Bike c set c.engine = ?2 where c.id = ?1")
    void updateEngine(Integer id, Engine engine);
}