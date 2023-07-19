package ru.ustinov.autopark.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ustinov.autopark.model.Brand;
import ru.ustinov.autopark.repository.BrandRepository;
import ru.ustinov.autopark.util.BrandNotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
@Transactional(readOnly = true)
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }
    @Override
    public Brand findById(Integer id) {
        log.info("Brand findById started");
        Optional<Brand> foundBrand = brandRepository.findById(id);
        log.info("Brand foundBrand={}", foundBrand);
        log.info("Brand findById ended");
        return foundBrand.orElseThrow(() -> new BrandNotFoundException("Brand not found by id=" + id));
    }
    @Override
    public Brand findByBrand(String brand) {
        log.info("Brand findByBrand started");
        Optional<Brand> foundBrand = brandRepository.findByBrand(brand);
        log.info("Brand foundBrand={}", foundBrand);
        log.info("Brand findByBrand ended");
        return foundBrand.orElseThrow(() -> new BrandNotFoundException("Brand not found by brand=" + brand));
    }
    @Override
    public List<Brand> findAll() {
        log.info("Brand findAll started");
        log.info("Brand findAll ended");
        return brandRepository.findAll();
    }

    @Override
    public Integer countAllBy() {
        log.info("Brand findAll started");
        log.info("Brand findAll ended");
        return brandRepository.countAllBy();
    }
    @Override
    public Set<String> findAllCarsAndBikesById(Integer id) {
        log.info("Brand findAllCarsAndBikesById started");
        Set<String> set = new HashSet<>();
        this.findById(id).getCars().forEach(car -> set.add(car.toString()));
        this.findById(id).getBikes().forEach(bike -> set.add(bike.toString()));
        log.info("Brand findAllCarsAndBikesById ended");
        return set;
    }
    @Override
    public Set<String> findAllCarsByBrand(String brand) {
        log.info("Brand findAllCarsByBrand started");
        Set<String> setCars = new HashSet<>();
        this.findByBrand(brand).getCars().forEach(car -> setCars.add(car.toString()));
        log.info("Brand findAllCarsByBrand ended");
        return setCars;
    }
    @Override
    public Integer countAllCarsByBrand(String brand) {
        log.info("Brand countAllCarsByBrand started");
        log.info("Brand countAllCarsByBrand ended");
        return this.findAllCarsByBrand(brand).size();
    }
    @Override
    public Set<String> findAllBikesByBrand(String brand) {
        log.info("Brand findAllBikesByBrand started");
        Set<String> setBikes = new HashSet<>();
        this.findByBrand(brand).getBikes().forEach(bike -> setBikes.add(bike.toString()));
        log.info("Brand findAllBikesByBrand ended");
        return setBikes;
    }
    @Override
    public Integer countAllBikesByBrand(String brand) {
        log.info("Brand countAllBikesByBrand started");
        log.info("Brand countAllBikesByBrand ended");
        return this.findAllBikesByBrand(brand).size();
    }
    @Override
    public Set<String> findAllCarsAndBikesByBrand(String brand) {
        log.info("Brand findAllCarsAndBikesByBrand started");
        Set<String> set = this.findAllCarsByBrand(brand);
        set.addAll(this.findAllBikesByBrand(brand));
        log.info("Brand findAllCarsAndBikesByBrand ended");
        return set;
    }
    @Override
    public Integer countAllCarsAndBikesByBrand(String brand) {
        log.info("Brand countAllCarsAndBikesByBrand started");
        log.info("Brand countAllCarsAndBikesByBrand ended");
        return this.findAllCarsAndBikesByBrand(brand).size();
    }
    @Override
    @Transactional
    public void save(Brand brand) {
        log.info("Brand save started");
        brandRepository.save(brand);
        log.info("Brand save ended");
    }
    @Override
    @Transactional
    public void updateBrand(Brand oldBrand, Brand newBrand) {
        log.info("Brand updateBrand started");
        oldBrand.setBrand(newBrand.getBrand());
        brandRepository.save(oldBrand);
        log.info("Brand updateBrand ended");
    }
    @Override
    @Transactional
    public void updateBrandById(Integer id, Brand newBrand) {
        log.info("Brand updateBrandById started");
        Brand brand = this.findById(id);
        this.updateBrand(brand, newBrand);
        log.info("Brand updateBrandById ended");
    }
    @Override
    @Transactional
    public void updateBrandByBrand(String oldBrand, Brand newBrand) {
        log.info("Brand updateBrandByBrand started");
        Brand brand = this.findByBrand(oldBrand);
        this.updateBrand(brand, newBrand);
        log.info("Brand updateBrandByBrand ended");
    }
}
