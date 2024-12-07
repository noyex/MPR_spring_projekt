package pl.edu.pjatk.LAB_2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.LAB_2.model.Brand;
import pl.edu.pjatk.LAB_2.model.Car;
import pl.edu.pjatk.LAB_2.service.BrandService;
import pl.edu.pjatk.LAB_2.service.CarService;

import java.io.IOException;
import java.security.PublicKey;
import java.util.List;

@RestController

public class MyRestController {
    private final CarService carService;
    private final BrandService brandService;

    @Autowired
    public MyRestController(CarService carService, BrandService brandService) {
        this.carService = carService;
        this.brandService = brandService;
    }

//    @GetMapping("/brands/remove-duplicates")
//    public ResponseEntity<String> removeDuplicates() {
//        brandService.removeDuplicateBrands();
//        return ResponseEntity.ok("Duplikaty zostały usunięte.");
//    }

    @GetMapping("car/model/{model}")
    public ResponseEntity<List<Car>> getByModel(@PathVariable String model) {
        return new ResponseEntity<>(this.carService.getCarByModel(model), HttpStatus.OK);
    }

    @GetMapping("car/all")  // <-- endpoint
    public ResponseEntity<List<Car>> getAll(){
        return new ResponseEntity<>(this.carService.getCarList(), HttpStatus.OK);
    }

    @GetMapping("car/brand/all")
    public ResponseEntity<List<Brand>> getAllBrands(){
        return new ResponseEntity<>(this.brandService.getBrandList(), HttpStatus.OK);
    }

    @GetMapping("car/{id}")
    public ResponseEntity<Car> get(@PathVariable Long id){
            return new ResponseEntity<>(this.carService.getCar(id), HttpStatus.OK);
    }

    @GetMapping("car/brand/{brand}")
    public ResponseEntity<List<Car>> getByBrand(@PathVariable String brand){
        List<Car> cars = carService.getCarsByBrand(brand);
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @GetMapping("/car/{id}/pdf")
    public ResponseEntity<byte[]> getPdf(@PathVariable Long id) throws IOException {
        byte[] pdfContent = carService.generatePDF(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "car_certificate_" + id + ".pdf");
        return new ResponseEntity<>(pdfContent, headers, HttpStatus.OK);
    }

    @PostMapping("car")
    public ResponseEntity<Void> addCar(@RequestBody Car car){
        this.carService.add(car);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("car/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id){
        this.carService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("car/{id}")
    public ResponseEntity<Void> updateCar(@PathVariable Long id,  @RequestBody Car car){
        this.carService.update(id, car);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
