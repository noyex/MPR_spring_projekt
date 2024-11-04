package pl.edu.pjatk.LAB_2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjatk.LAB_2.model.Car;

import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
    List<Car> findByModel(String model);
    List<Car> findByCharToIntSum(int charToIntSum);
}
