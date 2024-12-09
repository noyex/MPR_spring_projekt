package pl.edu.pjatk.mpr_project.service;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.edu.pjatk.mpr_project.model.Brand;
import pl.edu.pjatk.mpr_project.repository.BrandRepository;

@Component
public class StringToBrandService implements Converter<String, Brand> {
    private final BrandRepository brandRepository;
    public StringToBrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public Brand convert(String source) {
        if (source == null || source.trim().isEmpty()) {
            return null;
        }
        return brandRepository.findByName(source)
                .orElseGet(() -> brandRepository.save(new Brand(source.trim())));
    }
}
