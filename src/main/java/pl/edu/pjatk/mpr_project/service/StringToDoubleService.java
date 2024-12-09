package pl.edu.pjatk.mpr_project.service;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToDoubleService implements Converter<String, Double> {

    @Override
    public Double convert(String source) {
        try {

            String formattedSource = source.replace(",", ".");
            return Double.parseDouble(formattedSource);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format: " + source, e);
        }
    }
}