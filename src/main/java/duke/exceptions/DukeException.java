package duke.exceptions;

/**
 * Represents an exception specific to Duke.
 */
public class DukeException extends Exception {

    /**
     * Constructs a DukeException object.
     * @param errMsg
     */
    public DukeException(String errMsg) {
        super(errMsg);
    }
}
