package pl.edu.pjatk.mpr_project;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.edu.pjatk.mpr_project.repository.BrandRepository;
import pl.edu.pjatk.mpr_project.repository.CarRepository;
import pl.edu.pjatk.mpr_project.service.BrandService;
import pl.edu.pjatk.mpr_project.service.CarService;
import pl.edu.pjatk.mpr_project.service.StringUtilsService;

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
