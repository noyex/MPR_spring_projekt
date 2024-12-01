package pl.edu.pjatk.LAB_2.exceptions;

public class BrandNotFoundException extends RuntimeException {
    public BrandNotFoundException() {
        super("Brand not found");
    }
}
