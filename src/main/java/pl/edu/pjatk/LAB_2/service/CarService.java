package pl.edu.pjatk.LAB_2.service;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import pl.edu.pjatk.LAB_2.model.Car;
import pl.edu.pjatk.LAB_2.repository.CarRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CarService {
    List<Car> carList = new ArrayList<>();

    private StringUtilsService stringUtilsService;
    private CarRepository repository;

    public CarService(CarRepository repository, StringUtilsService stringUtilsService) {
        this.repository = repository;
        this.stringUtilsService = stringUtilsService;
        this.repository.save(new Car("RS6", "black"));
        this.repository.save(new Car("RS7", "red"));
        this.repository.save(new Car("R8", "yellow"));
    }

    public List<Car> getCarByModel(String model) {
     return this.repository.findByModel(model);
    }
    public List<Car> getCarList() {
        List<Car> cars = (List<Car>) this.repository.findAll(); ;
        for (Car car : cars) {
            car.setModel(stringUtilsService.toProperCase(car.getModel()));
            car.setColor(stringUtilsService.toProperCase(car.getColor()));
        }
        return cars;
    }
    public void add(@RequestBody Car car){
        car.setModel(stringUtilsService.toProperCase(car.getModel()));
        car.setColor(stringUtilsService.toProperCase(car.getColor()));
        this.repository.save(car);
    }
    public Optional<Car> getCar(Long id) {
        Optional<Car> carOptional = this.repository.findById(id);
        carOptional.ifPresent(car -> {
            car.setModel(stringUtilsService.toProperCase(car.getModel()));
            car.setColor(stringUtilsService.toProperCase(car.getColor()));
        });
        return carOptional;
    }
    public void delete(Long id){
        this.repository.deleteById(id);
    }
    public void update(Long id, Car car){
        Optional<Car> existingCarOptional = repository.findById(car.getId());

        if(existingCarOptional.isPresent()){
            Car existingCar = existingCarOptional.get();
            existingCar.setModel(stringUtilsService.toProperCase(car.getModel()));
            existingCar.setColor(stringUtilsService.toProperCase(car.getColor()));
            this.repository.save(existingCar);
        }else {
            throw new RuntimeException("Samochód o ID " + car.getId() + " nie istnieje.");
        }
    }
}
