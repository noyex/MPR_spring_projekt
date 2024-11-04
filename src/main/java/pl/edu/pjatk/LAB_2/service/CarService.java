package pl.edu.pjatk.LAB_2.service;

import org.springframework.stereotype.Component;
import pl.edu.pjatk.LAB_2.exceptions.CarAlreadyExistsException;
import pl.edu.pjatk.LAB_2.exceptions.CarNotFoundExceptions;
import pl.edu.pjatk.LAB_2.exceptions.CarWrongDataInputException;
import pl.edu.pjatk.LAB_2.model.Car;
import pl.edu.pjatk.LAB_2.repository.CarRepository;
import java.util.List;
import java.util.Optional;

@Component
public class CarService {

    private final StringUtilsService stringUtilsService;
    private final CarRepository repository;

    public CarService(CarRepository repository, StringUtilsService stringUtilsService) {
        this.repository = repository;
        this.stringUtilsService = stringUtilsService;

        add(new Car("RS6", "black"));
        add(new Car("RS7", "red"));
        add(new Car("R8", "yellow"));
    }

    public List<Car> getCarByModel(String model) {
        if(model == null || model.isEmpty()) throw new CarWrongDataInputException();
        List<Car> cars = repository.findByModel(model);
        if(cars.isEmpty()) throw new CarNotFoundExceptions();
        return cars;
    }

    public List<Car> getCarList() {
        List<Car> cars = (List<Car>) this.repository.findAll();
        if(cars.isEmpty()) throw new CarNotFoundExceptions();
        for (Car car : cars) {
            car.setModel(stringUtilsService.toProperCase(car.getModel()));
            car.setColor(stringUtilsService.toProperCase(car.getColor()));
        }
        return cars;
    }

    public void checkIfCarExists(Car car) {
        List<Car> carsBySum = this.repository.findByCharToIntSum(car.getCharToIntSum());
        if (!carsBySum.isEmpty()) {
            throw new CarAlreadyExistsException();
        }
    }

    public void add(Car car) {
        carWrongDataInputException(car);
        checkIfCarExists(car);
        car.setModel(stringUtilsService.toProperCase(car.getModel()));
        car.setColor(stringUtilsService.toProperCase(car.getColor()));
        this.repository.save(car);
    }

    public Car getCar(Long id) {
        Optional<Car> carOptional = this.repository.findById(id);
        if(carOptional.isEmpty()) throw new CarNotFoundExceptions();

        Car car = carOptional.get();
        car.setModel(stringUtilsService.toProperCase(car.getModel()));
        car.setColor(stringUtilsService.toProperCase(car.getColor()));
        return car;
    }

    public void delete(Long id){
        Optional<Car> carOptional = this.repository.findById(id);
        if(carOptional.isEmpty()) throw new CarNotFoundExceptions();

        this.repository.deleteById(id);
    }

    public void update(Long id, Car car){
        carWrongDataInputException(car);

        Optional<Car> carOptional = this.repository.findById(id);

        if(carOptional.isEmpty()) throw new CarNotFoundExceptions();

        Car carToUpdate = carOptional.get();
        checkIfCarExists(car);

        carToUpdate.setModel(stringUtilsService.toProperCase(car.getModel()));
        carToUpdate.setColor(stringUtilsService.toProperCase(car.getColor()));
        carToUpdate.setCharToIntSum(car.getCharToIntSum());

        this.repository.save(carToUpdate);
    }

    public void carWrongDataInputException(Car car){
        if(car.getModel() == null || car.getModel().isEmpty()) throw new CarWrongDataInputException();
        if(car.getColor() == null || car.getColor().isEmpty()) throw new CarWrongDataInputException();
    }
}

