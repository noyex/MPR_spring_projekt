package pl.edu.pjatk.mpr_project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.pjatk.mpr_project.exceptions.StringUtilsWrongInputException;
import pl.edu.pjatk.mpr_project.service.StringUtilsService;

import static org.junit.jupiter.api.Assertions.*;

public class StringUtilsServiceTest {

    private StringUtilsService stringUtilsService;

    @BeforeEach
    public void setUp() {
        stringUtilsService = new StringUtilsService();
    }

    //test toProperCase
    @Test
    public void testToProperCaseSuccess_RandomCases_DoesNotThrowException() {
        String input = "tEstStrIng";
        String expected = "Teststring";

        String actual = stringUtilsService.toProperCase(input);

        assertEquals(expected, actual);
        assertDoesNotThrow(()-> stringUtilsService.toProperCase(input));
    }
    @Test
    public void testToProperCaseFailure_EmptyString_ThrowsException () {
        String input = "";
        assertThrows(StringUtilsWrongInputException.class, ()-> stringUtilsService.toProperCase(input));
    }
    @Test
    public void testToProperCase_NullInput_ThrowsException () {
        assertThrows(StringUtilsWrongInputException.class, ()-> stringUtilsService.toProperCase(null));
    }

    //test toUpperCase
    @Test
    public void testToUpperCaseSuccess_RandomCases_DoesNotThrowException() {
        String input = "tEstSTring";
        String expected = "TESTSTRING";

        String actual = stringUtilsService.toUpperCase(input);

        assertEquals(expected, actual);
        assertDoesNotThrow(()-> stringUtilsService.toUpperCase(input));
    }
    @Test
    public void testToUpperCaseFailure_EmptyString_ThrowsException () {
        String input = "";
        assertThrows(StringUtilsWrongInputException.class, ()-> stringUtilsService.toUpperCase(input));
    }
    @Test
    public void testToUpperCase_NullInput_ThrowsException () {
        assertThrows(StringUtilsWrongInputException.class, ()-> stringUtilsService.toUpperCase(null));
    }

    //test toLowerCase
    @Test
    public void testToLowerCaseSuccess_RandomCases_DoesNotThrowException() {
        String input = "tEstSTring";
        String expected = "teststring";

        String actual = stringUtilsService.toLowerCase(input);

        assertEquals(expected, actual);
        assertDoesNotThrow(()-> stringUtilsService.toLowerCase(input));
    }
    @Test
    public void testToLowerCaseFailure_EmptyString_ThrowsException () {
        String input = "";
        assertThrows(StringUtilsWrongInputException.class, ()-> stringUtilsService.toLowerCase(input));
    }
    @Test
    public void testToLowerCase_NullInput_ThrowsException () {
        assertThrows(StringUtilsWrongInputException.class, ()-> stringUtilsService.toLowerCase(null));
    }

}