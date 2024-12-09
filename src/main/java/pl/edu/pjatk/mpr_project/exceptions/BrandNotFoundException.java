package pl.edu.pjatk.mpr_project.exceptions;

public class BrandNotFoundException extends RuntimeException {
    public BrandNotFoundException() {
        super("Brand not found");
    }
}
