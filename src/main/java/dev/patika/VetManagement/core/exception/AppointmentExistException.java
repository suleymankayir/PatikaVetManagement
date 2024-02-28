package dev.patika.VetManagement.core.exception;

public class AppointmentExistException extends RuntimeException{
    public AppointmentExistException(String message) {
        super(message);
    }
}
