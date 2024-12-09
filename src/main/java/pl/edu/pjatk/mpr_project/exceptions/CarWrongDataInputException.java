package pl.edu.pjatk.mpr_project.exceptions;

public class CarWrongDataInputException extends RuntimeException{
    public CarWrongDataInputException() {
        super("Wrong data input!");
    }
}
