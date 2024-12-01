package pl.edu.pjatk.LAB_2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjatk.LAB_2.model.Brand;
import pl.edu.pjatk.LAB_2.model.Car;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
    List<Car> findByModel(String model);
    List<Car> findByCharToIntSum(int charToIntSum);
    List<Car> findByBrand(Brand brand);
}
