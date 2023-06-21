package duke.exceptions;

public class DukeException extends Exception {
    /**
     * The DukeException function is a constructor that takes in an error message as a string and returns the DukeException object.
     *
     *
     * @param String errMsg Pass the error message to the super class
     *
     * @return A string
     *
     * @docauthor Trelent
     */
    public DukeException(String errMsg) {
        super(errMsg);
    }
}
