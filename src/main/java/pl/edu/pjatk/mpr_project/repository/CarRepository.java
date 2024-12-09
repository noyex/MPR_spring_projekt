package pl.edu.pjatk.mpr_project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjatk.mpr_project.model.Brand;
import pl.edu.pjatk.mpr_project.model.Car;

import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
    List<Car> findByModel(String model);
    List<Car> findByCharToIntSum(int charToIntSum);
    List<Car> findByBrand(Brand brand);
    List<Car> findByModelContainingIgnoreCaseOrBrandNameContainingIgnoreCase(String brand, String model);
}
