package duke.exceptions;

/**
 * The DukeException class is a custom exception class in Java that extends the Exception class and takes in an error
 * message as a parameter.
 */
public class DukeException extends Exception {
    public DukeException(String errMsg) {
        super(errMsg);
    }
}
