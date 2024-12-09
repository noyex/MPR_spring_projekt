package pl.edu.pjatk.mpr_project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class MyExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value={CarNotFoundExceptions.class})
    public ResponseEntity<Object> handleNotFound(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value={CarAlreadyExistsException.class})
    public ResponseEntity<Object> handleAlreadyExists(CarAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value={CarWrongDataInputException.class})
    public ResponseEntity<Object> handleWrongData(CarWrongDataInputException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value={StringUtilsWrongInputException.class})
    public ResponseEntity<Object> handleWrongInput(StringUtilsWrongInputException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {BrandNotFoundException.class})
    public ResponseEntity<Object> handleBrandNotFound(BrandNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
