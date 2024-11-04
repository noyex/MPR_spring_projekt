package pl.edu.pjatk.LAB_2.exceptions;

public class CarAlreadyExistsException extends RuntimeException {
    public CarAlreadyExistsException() {
        super("Car already exists");
    }
}
