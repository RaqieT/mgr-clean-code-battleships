package pl.dabrowska.michalowski.battleships.exception;

public class WrongInputException extends Exception {
    public WrongInputException(String message) {
        super(message);
    }

    public WrongInputException(String message, Throwable cause) {
        super(message, cause);
    }
}
