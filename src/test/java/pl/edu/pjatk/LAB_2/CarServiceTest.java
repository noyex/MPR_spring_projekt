package pl.edu.pjatk.LAB_2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.edu.pjatk.LAB_2.model.Car;
import pl.edu.pjatk.LAB_2.repository.CarRepository;
import pl.edu.pjatk.LAB_2.service.CarService;
import pl.edu.pjatk.LAB_2.service.StringUtilsService;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class CarServiceTest {
    @Mock
    private CarRepository repository;

    @Mock
    private StringUtilsService stringUtilsService;

    @InjectMocks
    private CarService carService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testAddCar() {
        Car car = new Car("testModel", "testColor");


        when(stringUtilsService.toProperCase(car.getModel())).thenReturn("Testmodel");
        when(stringUtilsService.toProperCase(car.getColor())).thenReturn("Testcolor");

        carService.add(car);


        verify(repository, times(1)).save(car);


        assertEquals("Testmodel", car.getModel());
        assertEquals("Testcolor", car.getColor());
    }
    @Test
    void testGetCarList() {
        Car car1 = new Car("carModel1", "carColor1");
        Car car2 = new Car("carModel2", "carColor2");

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
    }

    @Test
    void testGetCar() {
        Car car = new Car("carModel", "carColor");
        when(repository.findById(1L)).thenReturn(Optional.of(car));
        when(stringUtilsService.toProperCase(car.getModel())).thenReturn("Carmodel");
        when(stringUtilsService.toProperCase(car.getColor())).thenReturn("Carcolor");

        Optional<Car> result = carService.getCar(1L);

        assertTrue(result.isPresent());
        assertEquals("Carmodel", result.get().getModel());
        assertEquals("Carcolor", result.get().getColor());
    }

    @Test
    void testGetCarNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        Optional<Car> result = carService.getCar(1L);

        assertFalse(result.isPresent());
    }
}
