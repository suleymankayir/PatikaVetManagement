package dev.patika.VetManagement.core.exception;

public class EntityAlreadyExistException extends RuntimeException{
    public EntityAlreadyExistException(Long id, Class<?> tClass){
        super(id + " ID'li "+ tClass.getSimpleName() + " zaten kayıtlı");
    }
}
