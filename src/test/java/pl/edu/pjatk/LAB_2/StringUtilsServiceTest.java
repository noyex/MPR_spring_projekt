package pl.edu.pjatk.LAB_2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.pjatk.LAB_2.service.StringUtilsService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class StringUtilsServiceTest {

    private StringUtilsService stringUtilsService;

    @BeforeEach
    public void setUp() {
        stringUtilsService = new StringUtilsService();
    }

    @Test
    public void toProperCaseSuccess() {
        String input = "tEstStrIng";
        String expected = "Teststring";

        String actual = stringUtilsService.toProperCase(input);

        assertEquals(expected, actual);
    }

    @Test
    public void upperCaseSuccess() {
        String input = "testString";
        String expected = "TESTSTRING";

        String actual = stringUtilsService.toUpperCase(input);

        assertEquals(expected, actual);
    }

    @Test
    public void stringIsEmpty() {
        String input = "";
        String expected = "";

        String actual = stringUtilsService.toProperCase(input);

        assertEquals(expected, actual);
    }

    @Test
    public void toProperCaseNullInput() {
        String actual = stringUtilsService.toProperCase(null);
        assertNull(actual);
    }
}