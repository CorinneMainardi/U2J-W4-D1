package it.epicode.U2J_W4_D1.exceptions;

public class UploadException extends RuntimeException {

  public UploadException(String message) {
    super(message);
  }

  public UploadException(String message, Throwable cause) {
    super(message, cause);
  }
}