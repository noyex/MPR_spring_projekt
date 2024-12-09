package pl.edu.pjatk.mpr_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.mpr_project.model.Brand;
import pl.edu.pjatk.mpr_project.model.Car;
import pl.edu.pjatk.mpr_project.repository.BrandRepository;
import pl.edu.pjatk.mpr_project.service.CarService;

@Controller
@RequestMapping("/view")
public class MyViewController {

    private final CarService carService;
    private final BrandRepository brandRepository;

    public MyViewController(CarService carService, BrandRepository brandRepository) {
        this.carService = carService;
        this.brandRepository = brandRepository;
    }

    @GetMapping("/all")
    public String viewAllCar(Model model) {
        model.addAttribute("carList", carService.getCarList());
        return "viewAll";
    }

    @GetMapping("/search")
    public String searchCars(@RequestParam String query, Model model) {
        model.addAttribute("carList", carService.searchCars(query));
        return "viewAll";
    }

    @GetMapping("/addForm")
    public String displayAddForm(Model model) {
        Car car = new Car();
        car.setPostAccident(false);
        model.addAttribute("car", car);
        return "addOrUpdateForm";
    }

    @PostMapping("/save")
    public String saveCar(@ModelAttribute("car") Car car) {
        String brandName = car.getBrand().getName();
        Brand brand = brandRepository.findByName(brandName)
                .orElseGet(() -> brandRepository.save(new Brand(brandName)));
        car.setBrand(brand);

        if (car.getId() == null) {
            carService.add(car);
        } else {
            carService.update(car.getId(), car);
        }

        return "redirect:/view/all";
    }

    @GetMapping("/update/{id}")
    public String updateCarForm(@PathVariable Long id, Model model) {
        Car car = carService.getCar(id);
        model.addAttribute("car", car);
        return "addOrUpdateForm";
    }

    @GetMapping("/delete/{id}")
    public String deleteCar(@PathVariable Long id) {
        carService.delete(id);
        return "redirect:/view/all";
    }
}