package pl.edu.pjatk.LAB_2.services;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import pl.edu.pjatk.LAB_2.model.Car;

import java.util.ArrayList;
import java.util.List;

@Component
public class CarService {
    List<Car> carList = new ArrayList<>();

    public CarService(List<Car> carList) {
        this.carList.add(new Car("RS6", "black"));
        this.carList.add(new Car("RS7", "red"));
        this.carList.add(new Car("R8", "yellow"));
    }
    public List<Car> getCarList() {
        return this.carList;
    }
    public void add(@RequestBody Car car){
        this.carList.add(car);
    }
    public Car getCar(int id){
        return this.carList.get(id);
    }
}
