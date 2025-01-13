package it.epicode.U2J_W3_D5.exceptions;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(){}
    public AlreadyExistsException(String message) {
        super(message);
    }
}
