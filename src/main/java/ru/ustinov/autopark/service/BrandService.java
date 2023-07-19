package ru.ustinov.autopark.service;

import org.springframework.transaction.annotation.Transactional;
import ru.ustinov.autopark.model.Brand;

import java.util.List;
import java.util.Set;

public interface BrandService {
    Brand findById(Integer id);
    Brand findByBrand(String brand);
    List<Brand> findAll();
    Integer countAllBy();
    Set<String> findAllCarsAndBikesById(Integer id);
    Set<String> findAllCarsByBrand(String brand);
    Integer countAllCarsByBrand(String brand);
    Set<String> findAllBikesByBrand(String brand);
    Integer countAllBikesByBrand(String brand);
    Set<String> findAllCarsAndBikesByBrand(String brand);
    Integer countAllCarsAndBikesByBrand(String brand);
    @Transactional
    void save(Brand brand);
    @Transactional
    void updateBrand(Brand oldBrand, Brand newBrand);
    @Transactional
    void updateBrandById(Integer id, Brand newBrand);
    @Transactional
    void updateBrandByBrand(String oldBrand, Brand newBrand);

}
