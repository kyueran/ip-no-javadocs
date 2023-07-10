package duke.exceptions;

// Throws DukeException with the given error message. This is useful for catching exceptions that are thrown by a code generator
public class DukeException extends Exception {
    public DukeException(String errMsg) {
        super(errMsg);
    }
}
