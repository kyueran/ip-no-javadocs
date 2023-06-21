package duke.enums;

import static duke.ui.Ui.DATE_TIME_FORMAT;
import static duke.ui.Ui.LS;

import duke.exceptions.DukeException;

public enum CommandType {
    BYE("bye", new DukeException("")),
    LIST("list", new DukeException("")),
    MARK("mark", new DukeException("Invalid format for mark." + LS + "Usage: mark <number>")),
    UNMARK("unmark", new DukeException("Invalid format for unmark." + LS + "Usage: unmark <number>")),
    DELETE("delete", new DukeException("Invalid format for delete." + LS + "Usage: delete <number>")),
    FIND("find", new DukeException("Invalid format for find." + LS + "Usage: find <string>")),
    TODO("todo", new DukeException("Invalid format for todo." + LS + "Usage: todo <description>")),
    DEADLINE("deadline", new DukeException("Invalid format for Deadline." + LS
            + "Usage: deadline <description> /by <" + DATE_TIME_FORMAT + ">")),
    EVENT("event", new DukeException("Invalid format for Event." + LS
            + "Usage: event <description> /from <" + DATE_TIME_FORMAT + "> /to <" + DATE_TIME_FORMAT + ">")),
    INCORRECT("incorrect", new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-("));
    private String word;
    private DukeException e;

    /**
     * The CommandType function takes in a String word and an Exception e,
     * and returns the CommandType of the given word. If the given word is not a valid command,
     * then it throws an exception with message &quot;Invalid command type&quot;.

     *
     * @param String word Store the word that is being used to create a command
     * @param DukeException e Set the exception to be thrown when an invalid command is entered
     *
     * @return A commandtype object
     *
     * @docauthor Trelent
     */
    CommandType(String word, DukeException e) {
        this.word = word;
        this.e = e;
    }

    /**
     * The getErr function returns the DukeException object that was passed to the constructor.
     *
     *
     *
     * @return The dukeexception object that was passed to the constructor
     *
     * @docauthor Trelent
     */
    public DukeException getErr() {
        return this.e;
    }

    /**
     * The toString function returns the word that is stored in this object.
     *
     *
     *
     * @return The word stored in the object
     *
     * @docauthor Trelent
     */
    @Override
    public String toString() {
        return this.word;
    }
}
