package pl.edu.pjatk.LAB_2.service;

import jakarta.persistence.Entity;
import org.springframework.stereotype.Component;
import pl.edu.pjatk.LAB_2.model.Brand;
import pl.edu.pjatk.LAB_2.repository.BrandRepository;

import java.util.List;

@Component
public class BrandService {

    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;

        addBrandIfNotExists("Audi");
        addBrandIfNotExists("Mercedes");
        addBrandIfNotExists("BMW");
    }

    private void addBrandIfNotExists(String brandName) {
        if (brandRepository.findByName(brandName).isPresent()) {
            Brand brand = new Brand(brandName);
            brandRepository.save(brand);
        }
    }
}
