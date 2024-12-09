package pl.edu.pjatk.mpr_project.service;

import org.springframework.stereotype.Component;
import pl.edu.pjatk.mpr_project.exceptions.StringUtilsWrongInputException;

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
