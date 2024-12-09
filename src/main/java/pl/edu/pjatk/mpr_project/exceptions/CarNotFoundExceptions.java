package pl.edu.pjatk.mpr_project.exceptions;

public class CarNotFoundExceptions extends RuntimeException {
    public CarNotFoundExceptions(){
        super("Car not found!");
    }
}
