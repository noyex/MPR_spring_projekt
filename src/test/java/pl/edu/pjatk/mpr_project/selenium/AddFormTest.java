package pl.edu.pjatk.mpr_project.selenium;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddFormTest {
    private WebDriver webDriver;

    @BeforeEach
    public void setUp() {
        this.webDriver = new ChromeDriver();
    }

    @Test
    public void testAddForm() {
        AddFormPage page = new AddFormPage(webDriver);
        page.open()
                .fillModelInput("rs6")
                .fillBrandInput("audi")
                .fillColorInput("yellow")
                .fillEngineInput(1.4)
                .fillPriceInput(20000)
                .fillHorsePowerInput(140)
                .fillYearInput(2004)
                .submitForm();
    }

}
