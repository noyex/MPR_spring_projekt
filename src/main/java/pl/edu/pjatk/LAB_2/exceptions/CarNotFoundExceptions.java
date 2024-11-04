package pl.edu.pjatk.LAB_2.exceptions;

public class CarNotFoundExceptions extends RuntimeException {
    public CarNotFoundExceptions(){
        super("Car not found!");
    }
}
