package utez.edu.mx.Ejercicio.exception;

public class CustomBadRequestException extends RuntimeException {
    public CustomBadRequestException(String message) {
        super(message);
    }
}
