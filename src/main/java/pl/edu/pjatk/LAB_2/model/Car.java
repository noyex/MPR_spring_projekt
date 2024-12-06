package pl.edu.pjatk.LAB_2.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

@Entity
@JsonPropertyOrder({"id", "model", "brand", "price", "color", "engine", "horsePower", "year", "postAccident", "charToIntSum"})
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    @JsonProperty("brand")
    @JsonIgnoreProperties("id")
    private Brand brand;

    private String model;
    private double price;
    private String color;
    private double engine;
    private int horsePower;

    @Column(name = "car_year")
    private int year;

    private boolean postAccident;
    private int charToIntSum;


    public Car(){}

    public Car(String model, String color, Brand brand, double price, double engine, int year, boolean postAccident, int horsePower) {
        this.model = model;
        this.color = color;
        this.price = price;
        this.brand = brand;
        this.engine = engine;
        this.horsePower = horsePower;
        this.year = year;
        this.postAccident = postAccident;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getEngine() {
        return engine;
    }

    public void setEngine(double engine) {
        this.engine = engine;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isPostAccident() {
        return postAccident;
    }

    public void setPostAccident(boolean postAccident) {
        this.postAccident = postAccident;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public int getCharToIntSum() {
        charToIntSum = 0;
        int intEngine = (int) (engine*10);
        for(int i = 0; i < model.length(); i++){
            charToIntSum += model.toLowerCase().charAt(i);
        }
        for(int i = 0; i < color.length(); i++){
            charToIntSum += color.toLowerCase().charAt(i);
        }
        charToIntSum += year;
        charToIntSum += horsePower;
        charToIntSum += intEngine;

        if(postAccident){
            charToIntSum += 1;
        } else {
            charToIntSum += 2;
        }
        return charToIntSum;
    }

    public void setCharToIntSum(int charToIntSum) {
        this.charToIntSum = charToIntSum;
    }

}
