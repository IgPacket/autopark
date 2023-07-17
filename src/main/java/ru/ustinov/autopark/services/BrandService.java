package ru.ustinov.autopark.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ustinov.autopark.entities.Brand;
import ru.ustinov.autopark.repositories.BrandRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BrandService {
    private final BrandRepository brandRepository;
    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public Brand findByBrand(String brand) {
        Optional<Brand> foundBrand = brandRepository.findByBrand(brand);
        return foundBrand.orElse(null);
    }
    public List<Brand> findAll() {
        return brandRepository.findAll();
    }
    public Brand findById(Integer id) {
        Optional<Brand> foundBrand = brandRepository.findById(id);
        return foundBrand.orElse(null);
    }
    @Transactional
    public void save(Brand brand) {
        brandRepository.save(brand);
    }
}
