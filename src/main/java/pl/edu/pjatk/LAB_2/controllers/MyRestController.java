package pl.edu.pjatk.LAB_2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.LAB_2.model.Car;
import pl.edu.pjatk.LAB_2.services.CarService;

import java.util.List;

@RestController

public class MyRestController {
    private CarService carService;

    @Autowired
    public MyRestController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("car/all")  // <-- endpoint
    public List<Car> getAll(){
        return this.carService.getCarList();
    }
    @GetMapping("car/{id}")
        public Car get(@PathVariable int id){
            return this.carService.getCar(id);
        }

    @PostMapping("car")
    public void addCar(@RequestBody Car car){
        this.carService.add(car);
    }
    @DeleteMapping("car/{id}")
    public void deleteCar(@PathVariable int id){
        this.carService.delete(id);
    }
    @PutMapping("car/{id}")
    public void updateCar(@PathVariable int id, @RequestBody Car car){
        this.carService.update(id, car);
    }

}
