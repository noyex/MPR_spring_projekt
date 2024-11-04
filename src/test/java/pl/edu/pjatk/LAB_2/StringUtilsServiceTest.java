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

    //test toProperCase
    @Test
    public void testToProperCaseSuccess_RandomCases() {
        String input = "tEstStrIng";
        String expected = "Teststring";

        String actual = stringUtilsService.toProperCase(input);

        assertEquals(expected, actual);
    }
    @Test
    public void testToProperCaseFailure_EmptyString () {
        String input = "";
        String expected = "";

        String actual = stringUtilsService.toProperCase(input);

        assertEquals(expected, actual);
    }
    @Test
    public void testToProperCase_NullInput() {
        String actual = stringUtilsService.toProperCase(null);
        assertNull(actual);
    }

    //test toUpperCase
    @Test
    public void testToUpperCaseSuccess_RandomCases() {
        String input = "tEstSTring";
        String expected = "TESTSTRING";

        String actual = stringUtilsService.toUpperCase(input);

        assertEquals(expected, actual);
    }
    @Test
    public void testToUpperCaseFailure_EmptyString() {
        String input = "";
        String expected = "";

        String actual = stringUtilsService.toUpperCase(input);

        assertEquals(expected, actual);
    }
    @Test
    public void testToUpperCase_NullInput() {
        String actual = stringUtilsService.toUpperCase(null);
        assertNull(actual);
    }

    //test toLowerCase
    @Test
    public void testToLowerCaseSuccess_RandomCases() {
        String input = "tEstSTring";
        String expected = "teststring";

        String actual = stringUtilsService.toLowerCase(input);

        assertEquals(expected, actual);
    }
    @Test
    public void testToLowerCaseFailure_EmptyString() {
        String input = "";
        String expected = "";

        String actual = stringUtilsService.toLowerCase(input);

        assertEquals(expected, actual);
    }
    @Test
    public void testToLowerCase_NullInput() {
        String actual = stringUtilsService.toLowerCase(null);
        assertNull(actual);
    }

    // dorobic Exceptions do StringUtils!!!
}