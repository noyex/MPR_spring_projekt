package pl.edu.pjatk.LAB_2;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.edu.pjatk.LAB_2.repository.BrandRepository;
import pl.edu.pjatk.LAB_2.repository.CarRepository;
import pl.edu.pjatk.LAB_2.service.BrandService;
import pl.edu.pjatk.LAB_2.service.CarService;
import pl.edu.pjatk.LAB_2.service.StringUtilsService;

public class BrandServiceTest {

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

}
