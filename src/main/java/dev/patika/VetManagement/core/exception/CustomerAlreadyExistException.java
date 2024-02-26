package dev.patika.VetManagement.core.exception;

public class CustomerAlreadyExistException extends RuntimeException{
    public CustomerAlreadyExistException(Long id){
        super(id + " ID'li müşteri zaten kayıtlı");
    }
}
