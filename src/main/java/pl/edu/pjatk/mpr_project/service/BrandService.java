package pl.edu.pjatk.mpr_project.service;

import org.springframework.stereotype.Component;
import pl.edu.pjatk.mpr_project.exceptions.BrandNotFoundException;
import pl.edu.pjatk.mpr_project.model.Brand;
import pl.edu.pjatk.mpr_project.repository.BrandRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BrandService {

    private final BrandRepository brandRepository;
    private final StringUtilsService stringUtilsService;

    public BrandService(BrandRepository brandRepository, StringUtilsService stringUtilsService) {
        this.brandRepository = brandRepository;
        this.stringUtilsService = stringUtilsService;


    }

    public void addBrandIfNotExists(String brandName) {
        if (brandRepository.findByName(stringUtilsService.toProperCase(brandName)).isPresent()) {
            Brand brand = new Brand(stringUtilsService.toProperCase(brandName));
            brandRepository.save(brand);
        }
    }

    public List<Brand> getBrandList() {
        List<Brand> brands = (List<Brand>) this.brandRepository.findAll();
        if (brands.isEmpty()) throw new BrandNotFoundException();
        for (Brand brand : brands) {
            brand.setName(stringUtilsService.toProperCase(brand.getName()));
        }
        return brands;
    }
    public void removeDuplicateBrands() {
        List<Brand> brands = (List<Brand>) brandRepository.findAll();

        Map<String, Brand> uniqueBrands = new HashMap<>();

        List<Brand> duplicates = new ArrayList<>();

        for (Brand brand : brands) {
            String name = brand.getName().toLowerCase();
            if (uniqueBrands.containsKey(name)) {
                duplicates.add(brand);
            } else {
                uniqueBrands.put(name, brand);
            }
        }

        if (!duplicates.isEmpty()) {
            brandRepository.deleteAll(duplicates);
            System.out.println("Usunięto duplikaty marek: " + duplicates.size());
        } else {
            System.out.println("Brak duplikatów do usunięcia.");
        }
    }
}
