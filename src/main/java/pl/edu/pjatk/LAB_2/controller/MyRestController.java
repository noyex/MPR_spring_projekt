package pl.edu.pjatk.LAB_2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.LAB_2.model.Car;
import pl.edu.pjatk.LAB_2.service.CarService;

import java.util.List;
import java.util.Optional;

@RestController

public class MyRestController {
    private CarService carService;

    @Autowired
    public MyRestController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("car/model/{model}")
    public List<Car> getByModel(@PathVariable String model) {
        return this.carService.getCarByModel(model);
    }

    @GetMapping("car/all")  // <-- endpoint
    public List<Car> getAll(){
        return this.carService.getCarList();
    }
    @GetMapping("car/{id}")
        public Optional<Car> get(@PathVariable Long id){
            return this.carService.getCar(id);
        }

    @PostMapping("car")
    public void addCar(@RequestBody Car car){
        this.carService.add(car);
    }
    @DeleteMapping("car/{id}")
    public void deleteCar(@PathVariable Long id){
        this.carService.delete(id);
    }
    @PutMapping("car/{id}")
    public void updateCar(@PathVariable Long id, @RequestBody Car car){
        this.carService.update(id, car);
    }

}
