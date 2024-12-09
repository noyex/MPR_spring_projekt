package pl.edu.pjatk.mpr_project.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddFormPage {
    WebDriver webDriver;

    @FindBy(id="model")
    WebElement modelInput;

    @FindBy(id="brand")
    WebElement brandInput;

    @FindBy(id="color")
    WebElement colorInput;

    @FindBy(id="engine")
    WebElement engineInput;

    @FindBy(id="price")
    WebElement priceInput;

    @FindBy(id="horsePower")
    WebElement horsePowerInput;

    @FindBy(id="year")
    WebElement yearInput;

    @FindBy(id="submit")
    WebElement submitButton;


    public AddFormPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public AddFormPage open() {
        this.webDriver.get("http://localhost:8080/addForm");
        return this;
    }

    public AddFormPage fillBrandInput(String text) {
        this.brandInput.sendKeys(text);
        return this;
    }

    public AddFormPage fillModelInput(String text) {
        this.modelInput.sendKeys(text);
        return this;
    }

    public AddFormPage fillColorInput(String text) {
        this.colorInput.sendKeys(text);
        return this;
    }

    public AddFormPage fillEngineInput(double num) {
        this.engineInput.sendKeys(String.valueOf(num));
        return this;
    }

    public AddFormPage fillPriceInput(int num) {
        this.priceInput.sendKeys(String.valueOf(num));
        return this;
    }

    public AddFormPage fillHorsePowerInput(int num) {
        this.horsePowerInput.sendKeys(String.valueOf(num));
        return this;
    }

    public AddFormPage fillYearInput(int num) {
        this.yearInput.sendKeys(String.valueOf(num));
        return this;
    }
    public AddFormPage submitForm() {
        this.submitButton.click();
        return this;
    }
}
