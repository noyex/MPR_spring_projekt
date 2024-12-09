package pl.edu.pjatk.mpr_project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pl.edu.pjatk.mpr_project.exceptions.CarAlreadyExistsException;
import pl.edu.pjatk.mpr_project.exceptions.CarNotFoundExceptions;
import pl.edu.pjatk.mpr_project.exceptions.CarWrongDataInputException;
import pl.edu.pjatk.mpr_project.model.Brand;
import pl.edu.pjatk.mpr_project.model.Car;
import pl.edu.pjatk.mpr_project.repository.BrandRepository;
import pl.edu.pjatk.mpr_project.repository.CarRepository;
import pl.edu.pjatk.mpr_project.service.BrandService;
import pl.edu.pjatk.mpr_project.service.CarService;
import pl.edu.pjatk.mpr_project.service.StringUtilsService;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class CarServiceTest {

    @Mock
    private CarRepository repository;

    @Mock
    private BrandRepository brandRepository;

    @Mock
    private StringUtilsService stringUtilsService;

    @InjectMocks
    private CarService carService;

    @InjectMocks
    private BrandService brandService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // getCarByModel test
    @Test
    public void testGetCarByModelSuccess_DoesNotThrowException(){
        Brand audiBrand = new Brand("Audi");
        Car car = new Car("testModel", "testColor", audiBrand, 20000 ,2.2, 2020, true, 900);

        when(brandRepository.findByName("Audi")).thenReturn(Optional.of(audiBrand));
        when(repository.findByModel("testModel")).thenReturn(Collections.singletonList(car));

        List<Car> cars = carService.getCarByModel("testModel");

        verify(repository, times(1)).findByModel("testModel");

        assertEquals("testModel", cars.get(0).getModel());
        assertEquals("testColor", cars.get(0).getColor());
        assertEquals("Audi", cars.get(0).getBrand().getName());
        assertEquals(2.2, cars.get(0).getEngine(), 0.01);
        assertEquals(2020, cars.get(0).getYear());
        assertTrue(cars.get(0).isPostAccident());
        assertEquals(20000, cars.get(0).getPrice());
        assertEquals(900, cars.get(0).getHorsePower());
    }
    @Test
    public void testGetCarByModelNotFound_ThrowException() {
        when(repository.findByModel("testModel")).thenReturn(Collections.emptyList());
        assertThrows(CarNotFoundExceptions.class, () -> carService.getCarByModel("testModel"));
        verify(repository, times(1)).findByModel("testModel");
    }
    @Test
    public void testGetCarByModelWrongDataInput_ThrowsException(){
        when(repository.findByModel("")).thenReturn(Collections.emptyList());
        assertThrows(CarWrongDataInputException.class, () -> carService.getCarByModel(""));
    }

// add car test
    @Test
    public void testAddCarSuccess_DoesNotThrowsException() {
        Brand brand = new Brand("Audi");
        Car car = new Car("testModel", "testColor", brand, 200000, 2.2, 2020, false, 900);

        when(stringUtilsService.toProperCase(car.getModel())).thenReturn("Testmodel");
        when(stringUtilsService.toProperCase(car.getColor())).thenReturn("Testcolor");
        when(brandRepository.findByName("Audi")).thenReturn(Optional.of(brand));

        assertDoesNotThrow(() -> carService.add(car));
        verify(repository, times(1)).save(car);
        assertEquals("Testmodel", car.getModel());
        assertEquals("Testcolor", car.getColor());
    }
    @Test
    public void testAddCarAllDataWrong_ThrowException() {
        Car car = new Car("", "", null, 0,0, 0, true, 0);
        assertThrows(CarWrongDataInputException.class, () -> carService.add(car));
    }
    @Test
    public void testAddCarWrongDataModel_ThrowsException() {
        Brand brand = new Brand();
        Car car = new Car(null, "yellow", brand, 2000,1.5, 2000, true, 100);
        assertThrows(CarWrongDataInputException.class, () -> carService.add(car));
    }
    @Test
    public void testAddCarWrongDataColor_ThrowsException() {
        Brand brand = new Brand();
        Car car = new Car("RS6", null, brand, 2000,1.4, 2000, true, 100);
        assertThrows(CarWrongDataInputException.class, () -> carService.add(car));
    }
    @Test
    public void testAddCarWrongDataEngineTooLow_ThrowsException() {
        Brand brand = new Brand();
        Car car = new Car("RS6", "yellow", brand,2000 ,0, 2000, true, 100);
        assertThrows(CarWrongDataInputException.class, () -> carService.add(car));
    }
    @Test
    public void testAddCarWrongDataEngineTooHigh_ThrowsException() {
        Brand brand = new Brand();
        Car car = new Car("RS6", "yellow", brand, 2000,7.1, 2000, true, 100);
        assertThrows(CarWrongDataInputException.class, () -> carService.add(car));
    }
    @Test
    public void testAddCarWrongDataYearTooLow_ThrowsException() {
        Brand brand = new Brand();
        Car car = new Car("RS6", "yellow", brand, 2000,1.2, 1899, true, 100);
        assertThrows(CarWrongDataInputException.class, () -> carService.add(car));
    }
    @Test
    public void testAddCarWrongDataYearTooHigh_ThrowsException() {
        Brand brand = new Brand();
        Car car = new Car("RS6", "yellow", brand, 2000,1.2, 2025, true, 100);
        assertThrows(CarWrongDataInputException.class, () -> carService.add(car));
    }
    @Test
    public void testAddCarWrongDataHorsePowerTooLow_ThrowsException() {
        Brand brand = new Brand();
        Car car = new Car("RS6", "yellow", brand, 2000,1.2, 2000, true, 0);
        assertThrows(CarWrongDataInputException.class, () -> carService.add(car));
    }
    @Test
    public void testAddCarWrongDataHorsePowerTooHigh_ThrowsException() {
        Brand brand = new Brand();
        Car car = new Car("RS6", "yellow", brand, 2000,1.2, 2000, true, 2001);
        assertThrows(CarWrongDataInputException.class, () -> carService.add(car));
    }

    //getCarList test
    @Test
    void testGetCarListSuccess_DoesNotThrowException() {
        Car car1 = new Car("carModel1", "carColor1", null, 2000,0, 0, true, 0);
        Car car2 = new Car("carModel2", "carColor2", null, 2000,0, 0, true, 0);

        when(repository.findAll()).thenReturn(Arrays.asList(car1, car2));
        when(stringUtilsService.toProperCase(car1.getModel())).thenReturn("Carmodel1");
        when(stringUtilsService.toProperCase(car1.getColor())).thenReturn("Carcolor1");
        when(stringUtilsService.toProperCase(car2.getModel())).thenReturn("Carmodel2");
        when(stringUtilsService.toProperCase(car2.getColor())).thenReturn("Carcolor2");

        var carList = carService.getCarList();

        assertEquals(2, carList.size());
        assertEquals("Carmodel1", carList.get(0).getModel());
        assertEquals("Carcolor1", carList.get(0).getColor());
        assertEquals("Carmodel2", carList.get(1).getModel());
        assertEquals("Carcolor2", carList.get(1).getColor());
        assertDoesNotThrow(()-> carService.getCarList());
    }
    @Test
    void testGetCarListEmpty_ThrowsException() {
        when(repository.findAll()).thenReturn(Collections.emptyList());
        assertThrows(CarNotFoundExceptions.class, () -> carService.getCarList());
    }

//checkIfCarExists test
    @Test
    void testCheckIfCarExists_ThrowsException() {
        Car car = new Car("rs6", "black", null, 0,0, 0, true, 0);
        car.setCharToIntSum(793);
        when(repository.findByCharToIntSum(793)).thenReturn(Collections.singletonList(car));
        assertThrows(CarAlreadyExistsException.class, () -> carService.checkIfCarExists(car));
    }
    @Test
    void testCheckIfCarExists_DoesNotThrowException() {
        Car car = new Car("existingModel", "existingColor", null, 0,0, 0, true, 0);
        car.setCharToIntSum(124);

        when(repository.findByCharToIntSum(124)).thenReturn(Collections.emptyList());

        assertDoesNotThrow(()-> carService.checkIfCarExists(car));
    }

    // getCarByBrand test
//    @Test
//    void testGetCarByModelSuccess_DoesNotThrowException() {
//        Brand brand = new Brand("Audi");
//        Car car = new Car("testModel", "testColor", brand, 200000, 2.2, 2020, false, 900);
//
//        when(repository.findByModel("testModel")).thenReturn(Collections.singletonList(car));
//        when(brandRepository.findByName("Audi")).thenReturn(Optional.of(brand));
//
//        List<Car> cars = carService.getCarByModel("testModel");
//
//        verify(repository, times(1)).findByModel("testModel");
//        assertEquals(1, cars.size());
//        assertEquals("testModel", cars.get(0).getModel());
//    }

//    //getCar test
    @Test
    void testGetCarSuccess_DoesNotThrowException() {
        Brand brand = new Brand("Audi");
        Car car = new Car("testModel", "testColor", brand, 200000, 2.2, 2020, false, 900);

        when(repository.findById(1L)).thenReturn(Optional.of(car));
        when(stringUtilsService.toProperCase("testModel")).thenReturn("TestModel");
        when(stringUtilsService.toProperCase("testColor")).thenReturn("TestColor");

        Car result = carService.getCar(1L);

        verify(repository, times(1)).findById(1L);
        assertEquals("TestModel", result.getModel());
        assertEquals("TestColor", result.getColor());
    }
    @Test
    void testGetCarNotFound_ThrowsException() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(CarNotFoundExceptions.class, () -> carService.getCar(1L));
    }
//
//    //carDelete test
    @Test
    void testCarDeleteSuccess_DoesNotThrowException() {
        Car car = new Car("carModel1", "carColor1", null, 2000,0, 0, true, 0);
        when(repository.findById(1L)).thenReturn(Optional.of(car));
        carService.delete(1L);
        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).deleteById(1L);
        assertDoesNotThrow(( ) -> carService.delete(1L));
    }
    @Test
    void testCarDeleteFailure_ThrowsException() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(CarNotFoundExceptions.class, () -> carService.delete(1L));
    }

//    //carUpdate test
    @Test
    void testUpdateCarSuccess_DoesNotThrowException() {
        Long id = 1L;
        Brand brand = new Brand("Audi");
        Car car = new Car("carModel1", "carColor1", brand, 2000,1.4, 2000, true, 120);
        when(repository.findById(1L)).thenReturn(Optional.of(car));
        when(stringUtilsService.toProperCase(car.getModel())).thenReturn("Carmodel");
        when(stringUtilsService.toProperCase(car.getColor())).thenReturn("Carcolor");
        when(repository.save(car)).thenReturn(car);
        assertDoesNotThrow( () -> carService.update(1L,car));
        verify(repository, times(1)).save(car);
    }
    @Test
    void testUpdateCarNotFound_ThrowsException() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(CarNotFoundExceptions.class, () -> carService.update(1L,new Car("carModel1", "carColor1", null, 2000,1.2, 2000, true, 100)));
    }
    @Test
    void testUpdateCarAlreadyExists_ThrowsException() {
        Long id = 1L;
        Car car = new Car("rs6", "black", null, 2000,0, 0, true, 0);
        car.getCharToIntSum();
        when(repository.findById(1L)).thenReturn(Optional.of(car));
        when(repository.findByCharToIntSum(793)).thenReturn(Collections.singletonList(car));
        assertThrows(CarAlreadyExistsException.class, () -> carService.checkIfCarExists(car));
    }

//    //generatePDF test
    @Test
    void testGeneratePDFSuccess_DoesNotThrowException() throws IOException {

        Brand brand = new Brand("Audi");
        Car car = new Car("carModel", "carColor", brand, 20000, 2.0, 2020, false, 150);

        Long carId = 1L;

        Mockito.when(repository.findById(carId)).thenReturn(Optional.of(car));

        byte[] pdfContent = carService.generatePDF(carId);

        assertNotNull(pdfContent, "Generated PDF should not be null.");
        assertTrue(pdfContent.length > 0, "Generated PDF should not be empty.");
    }
    @Test
    void testGeneratePDF_CarNotFound_ThrowsException() {
        Long carId = 1L;

        Mockito.when(repository.findById(carId)).thenReturn(Optional.empty());

        assertThrows(CarNotFoundExceptions.class, () -> carService.generatePDF(carId));
    }
    @Test
    void testGeneratePDF_VariousCarData() throws IOException {
        Brand brand1 = new Brand("Tesla");
        Brand brand2 = new Brand("Toyota");

        Car car1 = new Car("ModelX", "Red", brand1, 100000, 3.0, 2022, true, 400);
        Car car2 = new Car("ModelY", "Blue", brand2, 80000, 2.5, 2021, false, 300);

        Long carId1 = 1L;
        Long carId2 = 2L;

        Mockito.when(repository.findById(carId1)).thenReturn(Optional.of(car1));
        Mockito.when(repository.findById(carId2)).thenReturn(Optional.of(car2));

        byte[] pdfContent1 = carService.generatePDF(carId1);
        byte[] pdfContent2 = carService.generatePDF(carId2);

        assertNotNull(pdfContent1, "Generated PDF for car1 should not be null.");
        assertNotNull(pdfContent2, "Generated PDF for car2 should not be null.");
        assertTrue(pdfContent1.length > 0, "Generated PDF for car1 should not be empty.");
        assertTrue(pdfContent2.length > 0, "Generated PDF for car2 should not be empty.");
    }

}
