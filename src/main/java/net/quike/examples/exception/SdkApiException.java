package net.quike.examples.exception;

public class SdkApiException extends RuntimeException {
  public SdkApiException(String msg) {
    super(msg);
  }

  public SdkApiException(String msg, Throwable cause) {
    super(msg, cause);
  }
}
