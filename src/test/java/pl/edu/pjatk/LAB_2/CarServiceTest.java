//package pl.edu.pjatk.LAB_2;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import pl.edu.pjatk.LAB_2.exceptions.CarAlreadyExistsException;
//import pl.edu.pjatk.LAB_2.exceptions.CarNotFoundExceptions;
//import pl.edu.pjatk.LAB_2.exceptions.CarWrongDataInputException;
//import pl.edu.pjatk.LAB_2.model.Car;
//import pl.edu.pjatk.LAB_2.repository.CarRepository;
//import pl.edu.pjatk.LAB_2.service.CarService;
//import pl.edu.pjatk.LAB_2.service.StringUtilsService;
//
//import java.io.IOException;
//import java.util.*;
//import java.util.function.BooleanSupplier;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//
//public class CarServiceTest {
//    @Mock
//    private CarRepository repository;
//
//    @Mock
//    private StringUtilsService stringUtilsService;
//
//    @InjectMocks
//    private CarService carService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    // getCarByModel test
//    @Test
//    public void testGetCarByModelSuccess_DoesNotThrowsException(){
//        Car car = new Car("testModel", "testColor");
//        when(repository.findByModel("testModel")).thenReturn(Collections.singletonList(car));
//
//        List<Car> cars = carService.getCarByModel("testModel");
//
//        verify(repository, times(1)).findByModel("testModel");
//
//        assertEquals("testModel", cars.getFirst().getModel());
//        assertEquals("testColor", cars.getFirst().getColor());
//        assertDoesNotThrow(() -> carService.getCarByModel("testModel"));
//    }
//    @Test
//    public void testGetCarByModelNotFound_ThrowException() {
//        when(repository.findByModel("testModel")).thenReturn(Collections.emptyList());
//        assertThrows(CarNotFoundExceptions.class, () -> carService.getCarByModel("testModel"));
//        verify(repository, times(1)).findByModel("testModel");
//    }
//    @Test
//    public void testGetCarByModelWrongDataInput_ThrowsException(){
//        when(repository.findByModel("")).thenReturn(Collections.emptyList());
//        assertThrows(CarWrongDataInputException.class, () -> carService.getCarByModel(""));
//    }
//
//    // add car test
//    @Test
//    public void testAddCarSuccess_DoesNotThrowsException() {
//        Car car = new Car("testModel", "testColor");
//
//        when(stringUtilsService.toProperCase(car.getModel())).thenReturn("Testmodel");
//        when(stringUtilsService.toProperCase(car.getColor())).thenReturn("Testcolor");
//
//        carService.add(car);
//
//        verify(repository, times(1)).save(car);
//
//        assertEquals("Testmodel", car.getModel());
//        assertEquals("Testcolor", car.getColor());
//        assertDoesNotThrow(() -> carService.add(car));
//    }
//    @Test
//    public void testAddCarWrongDataBoth_ThrowException() {
//        Car car = new Car("", "");
//        assertThrows(CarWrongDataInputException.class, () -> carService.add(car));
//    }
//    @Test
//    public void testAddCarWrongDataModel_ThrowsException() {
//        Car car = new Car("RS6", "");
//        assertThrows(CarWrongDataInputException.class, () -> carService.add(car));
//    }
//    @Test
//    public void testAddCarWrongDataColor_ThrowsException() {
//        Car car = new Car("RS6", "");
//        assertThrows(CarWrongDataInputException.class, () -> carService.add(car));
//    }
//
//    //getCarList test
//    @Test
//    void testGetCarListSuccess_DoesNotThrowException() {
//        Car car1 = new Car("carModel1", "carColor1");
//        Car car2 = new Car("carModel2", "carColor2");
//
//        when(repository.findAll()).thenReturn(Arrays.asList(car1, car2));
//        when(stringUtilsService.toProperCase(car1.getModel())).thenReturn("Carmodel1");
//        when(stringUtilsService.toProperCase(car1.getColor())).thenReturn("Carcolor1");
//        when(stringUtilsService.toProperCase(car2.getModel())).thenReturn("Carmodel2");
//        when(stringUtilsService.toProperCase(car2.getColor())).thenReturn("Carcolor2");
//
//        var carList = carService.getCarList();
//
//        assertEquals(2, carList.size());
//        assertEquals("Carmodel1", carList.get(0).getModel());
//        assertEquals("Carcolor1", carList.get(0).getColor());
//        assertEquals("Carmodel2", carList.get(1).getModel());
//        assertEquals("Carcolor2", carList.get(1).getColor());
//        assertDoesNotThrow(()-> carService.getCarList());
//    }
//    @Test
//    void testGetCarListEmpty_ThrowsException() {
//        when(repository.findAll()).thenReturn(Collections.emptyList());
//        assertThrows(CarNotFoundExceptions.class, () -> carService.getCarList());
//    }
//
//    //checkIfCarExists test
//    @Test
//    void testCheckIfCarExists_ThrowsException() {
//        Car car = new Car("rs6", "black");
//        car.setCharToIntSum(792);
//        when(repository.findByCharToIntSum(792)).thenReturn(Collections.singletonList(car));
//        assertThrows(CarAlreadyExistsException.class, () -> carService.checkIfCarExists(car));
//    }
//    @Test
//    void testCheckIfCarExists_DoesNotThrowException() {
//        Car car = new Car("existingModel", "existingColor");
//        car.setCharToIntSum(123);
//
//        when(repository.findByCharToIntSum(123)).thenReturn(Collections.emptyList());
//
//        assertDoesNotThrow(()-> carService.checkIfCarExists(car));
//    }
//
//    //getCar test
//    @Test
//    void testGetCarSuccess_DoesNotThrowException(){
//        Car car = new Car("carModel1", "carColor1");
//
//        when(repository.findById(1L)).thenReturn(Optional.of(car));
//        when(stringUtilsService.toProperCase(car.getModel())).thenReturn("Carmodel1");
//        when(stringUtilsService.toProperCase(car.getColor())).thenReturn("Carcolor1");
//
//        Car result = carService.getCar(1L);
//
//        verify(repository, times(1)).findById(1L);
//        assertEquals("Carmodel1", result.getModel());
//        assertEquals("Carcolor1", result.getColor());
//        assertDoesNotThrow(() -> carService.add(car));
//    }
//    @Test
//    void testGetCarNotFound_ThrowsException() {
//        Car car = new Car("carModel1", "carColor1");
//        when(repository.findById(1L)).thenReturn(Optional.empty());
//        assertThrows(CarNotFoundExceptions.class, () -> carService.getCar(1L));
//    }
//
//    //carDelete test
//    @Test
//    void testCarDeleteSuccess_DoesNotThrowException() {
//        Car car = new Car("carModel1", "carColor1");
//        when(repository.findById(1L)).thenReturn(Optional.of(car));
//        carService.delete(1L);
//        verify(repository, times(1)).findById(1L);
//        verify(repository, times(1)).deleteById(1L);
//        assertDoesNotThrow(( ) -> carService.delete(1L));
//    }
//    @Test
//    void testCarDeleteFailure_ThrowsException() {
//        when(repository.findById(1L)).thenReturn(Optional.empty());
//        assertThrows(CarNotFoundExceptions.class, () -> carService.delete(1L));
//    }
//
//    //carUpdate test
//    @Test
//    void testUpdateCarSuccess_DoesNotThrowException() {
//        Long id = 1L;
//        Car car = new Car("carModel", "carColor");
//        when(repository.findById(1L)).thenReturn(Optional.of(car));
//        when(stringUtilsService.toProperCase(car.getModel())).thenReturn("Carmodel");
//        when(stringUtilsService.toProperCase(car.getColor())).thenReturn("Carcolor");
//        when(repository.save(car)).thenReturn(car);
//        assertDoesNotThrow( () -> carService.update(1L,car));
//        verify(repository, times(1)).save(car);
//    }
//    @Test
//    void testUpdateCarNotFound_ThrowsException() {
//        when(repository.findById(1L)).thenReturn(Optional.empty());
//        assertThrows(CarNotFoundExceptions.class, () -> carService.update(1L,new Car("carModel", "carColor")));
//    }
//    @Test
//    void testUpdateCarAlreadyExists_ThrowsException() {
//        Long id = 1L;
//        Car car = new Car("rs6", "black");
//        car.setCharToIntSum(792);
//        when(repository.findById(1L)).thenReturn(Optional.of(car));
//        when(repository.findByCharToIntSum(792)).thenReturn(Collections.singletonList(car));
//        assertThrows(CarAlreadyExistsException.class, () -> carService.checkIfCarExists(car));
//    }
//
//    //generatePDF test
//    @Test
//    void testGeneratePDFSuccess_DoesNotThrowException() throws IOException {
//        Car car = new Car("carModel", "carColor");
//        Long carId = 1L;
//        Mockito.when(repository.findById(carId)).thenReturn(Optional.of(car));
//        byte[] pdfContent = carService.generatePDF(carId);
//        assertNotNull(pdfContent, "Generated PDF should not be null.");
//        assertTrue(pdfContent.length > 0, "Generated PDF should not be empty.");
//    }
//    @Test
//    void testGeneratePDF_CarNotFound_ThrowsException() {
//        Long carId = 1L;
//
//        Mockito.when(repository.findById(carId)).thenReturn(Optional.empty());
//
//        assertThrows(CarNotFoundExceptions.class, () -> carService.generatePDF(carId));
//    }
//    @Test
//    void testGeneratePDF_VariousCarData() throws IOException {
//        Car car1 = new Car("ModelX", "Red");
//        Car car2 = new Car("ModelY", "Blue");
//
//        Long carId1 = 1L;
//        Long carId2 = 2L;
//
//        Mockito.when(repository.findById(carId1)).thenReturn(Optional.of(car1));
//        Mockito.when(repository.findById(carId2)).thenReturn(Optional.of(car2));
//
//        byte[] pdfContent1 = carService.generatePDF(carId1);
//        byte[] pdfContent2 = carService.generatePDF(carId2);
//
//        assertNotNull(pdfContent1);
//        assertNotNull(pdfContent2);
//    }
//
//}
