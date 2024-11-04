package pl.edu.pjatk.LAB_2.service;

import org.springframework.stereotype.Component;

@Component
public class StringUtilsService {

    public String toUpperCase(String input) {
        return input != null ? input.toUpperCase() : null;
    }

    public String toProperCase(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0,1).toUpperCase() + input.substring(1).toLowerCase();
    }
}
