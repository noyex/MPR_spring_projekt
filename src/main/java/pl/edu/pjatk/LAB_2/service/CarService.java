package pl.edu.pjatk.LAB_2.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.springframework.stereotype.Component;
import pl.edu.pjatk.LAB_2.exceptions.CarAlreadyExistsException;
import pl.edu.pjatk.LAB_2.exceptions.CarNotFoundExceptions;
import pl.edu.pjatk.LAB_2.exceptions.CarWrongDataInputException;
import pl.edu.pjatk.LAB_2.model.Brand;
import pl.edu.pjatk.LAB_2.model.Car;
import pl.edu.pjatk.LAB_2.repository.BrandRepository;
import pl.edu.pjatk.LAB_2.repository.CarRepository;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class CarService {

    private final StringUtilsService stringUtilsService;
    private final CarRepository repository;
    private final BrandRepository brandRepository;

    public CarService(CarRepository repository, StringUtilsService stringUtilsService, BrandRepository brandRepository) {
        this.repository = repository;
        this.stringUtilsService = stringUtilsService;
        this.brandRepository = brandRepository;

        add(new Car("RS6", "black", getOrCreateBrand("Audi"), 3.2, 2017, false, 670));
    }

    private Brand getOrCreateBrand(String brandName) {
        Optional<Brand> brand = brandRepository.findByName(brandName);
        return brand.orElseGet(() -> brandRepository.save(new Brand(brandName)));
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

    public byte[] generatePDF(Long id) throws IOException {
        Optional<Car> carOptional = this.repository.findById(id);
        if (carOptional.isEmpty()) {
            throw new CarNotFoundExceptions();
        }
        Car car = carOptional.get();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try (PDDocument doc = new PDDocument()) {
            PDPage page = new PDPage();
            doc.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(doc, page)) {
                contentStream.setLineWidth(1f);

                contentStream.setNonStrokingColor(Color.LIGHT_GRAY);
                contentStream.addRect(50, 720, 500, 50);
                contentStream.fill();


                contentStream.beginText();
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 16);
                contentStream.setNonStrokingColor(0, 0, 0);
                contentStream.newLineAtOffset(50, 750);
                contentStream.showText("Ownership Certificate");
                contentStream.endText();


                contentStream.beginText();
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.TIMES_ROMAN), 12);
                contentStream.setLeading(14.5f);
                contentStream.newLineAtOffset(50, 700);

                contentStream.showText("Model: " + car.getModel());
                contentStream.newLine();
                contentStream.showText("Color: " + car.getColor());
                contentStream.newLine();

                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_OBLIQUE), 12);
                contentStream.showText("Thank you for choosing our dealership!");

                contentStream.endText();

                contentStream.setStrokingColor(0, 0, 0);
                contentStream.moveTo(50, 735);
                contentStream.lineTo(550, 735);
                contentStream.stroke();
            }

            doc.save(byteArrayOutputStream);
        }

        return byteArrayOutputStream.toByteArray();
    }
}

