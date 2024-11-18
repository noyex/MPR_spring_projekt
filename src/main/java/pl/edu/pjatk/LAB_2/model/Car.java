package pl.edu.pjatk.LAB_2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int charToIntSum;
    private String model;
    private String color;


    public Long getId() {
        return id;
    }

    public Car(){}

    public Car(String model, String color) {
        this.model = model;
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCharToIntSum() {
        charToIntSum = 0;
        for(int i = 0; i < model.length(); i++){
            charToIntSum += model.toLowerCase().charAt(i);
        }
        for(int i = 0; i < color.length(); i++){
            charToIntSum += color.toLowerCase().charAt(i);
        }
        return charToIntSum;
    }

    public void setCharToIntSum(int charToIntSum) {
        this.charToIntSum = charToIntSum;
    }

}
