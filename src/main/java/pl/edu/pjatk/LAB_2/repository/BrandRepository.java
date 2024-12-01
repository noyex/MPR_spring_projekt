package pl.edu.pjatk.LAB_2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjatk.LAB_2.model.Brand;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends CrudRepository<Brand, Integer> {
    Optional<Brand> findByName(String name);
}
