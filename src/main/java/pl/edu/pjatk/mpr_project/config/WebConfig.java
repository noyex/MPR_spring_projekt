package pl.edu.pjatk.mpr_project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.edu.pjatk.mpr_project.service.StringToBrandService;
import pl.edu.pjatk.mpr_project.service.StringToDoubleService;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final StringToBrandService stringToBrandService;
    private final StringToDoubleService stringToDoubleService;

    public WebConfig(StringToBrandService stringToBrandService) {
        this.stringToBrandService = stringToBrandService;
        this.stringToDoubleService = new StringToDoubleService();
    }
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToBrandService);
        registry.addConverter(stringToDoubleService);
    }
}
