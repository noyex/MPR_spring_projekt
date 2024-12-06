package pl.edu.pjatk.LAB_2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.edu.pjatk.LAB_2.model.Car;
import pl.edu.pjatk.LAB_2.service.CarService;

@Controller
public class MyViewController {

    private final CarService carService;

    public MyViewController(CarService carService) {
        this.carService = carService;
    }


    @GetMapping("view/all")
    public String viewAllCar(Model model){
        model.addAttribute("carList", carService.getCarList());
        return "viewAll";
    }

    @GetMapping("addForm")
    public String displayAddForm(Model model){
        model.addAttribute("car", new Car());
        return  "addForm";
    }

    @PostMapping("addForm")
    public String processAddForm(@ModelAttribute("car") Car car){
        this.carService.add(car);
        return "redirect:/view/all";
    }

}
