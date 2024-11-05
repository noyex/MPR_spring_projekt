package pl.edu.pjatk.LAB_2.service;

import org.springframework.stereotype.Component;
import pl.edu.pjatk.LAB_2.exceptions.StringUtilsWrongInputException;

@Component
public class StringUtilsService {

    public String toUpperCase(String input) {
        if(input == null || input.isEmpty()) throw new StringUtilsWrongInputException();
        return input.toUpperCase();
    }

    public String toLowerCase(String input) {
        if(input == null || input.isEmpty()) throw new StringUtilsWrongInputException();
        return input.toLowerCase();
    }

    public String toProperCase(String input) {
        if(input == null || input.isEmpty()) throw new StringUtilsWrongInputException();
        return input.substring(0,1).toUpperCase() + input.substring(1).toLowerCase();
    }
}
