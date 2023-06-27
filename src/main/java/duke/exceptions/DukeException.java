package duke.exceptions;

/**
 * Exception thrown when there is an error in the Duke program.
 */
public class DukeException extends Exception {
    public DukeException(String errMsg) {
        super(errMsg);
    }
}
