package ru.ustinov.autopark.repository;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.ustinov.autopark.model.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
    @Query("select b from Brand b where b.id = ?1")
    @NonNull
    Optional<Brand> findById(@NonNull Integer id);
    @Query("select b from Brand b where b.brand = ?1")
    Optional<Brand> findByBrand(@Size(max = 100) @NotNull String brand);
    @Query("select b from Brand b")
    @NonNull
    List<Brand> findAll();
    @Query("select count(b) from Brand b")
    Integer countAllBy();
}