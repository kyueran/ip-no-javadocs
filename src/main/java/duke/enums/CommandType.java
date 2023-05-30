package duke.enums;

import static duke.ui.Ui.DATE_TIME_FORMAT;
import static duke.ui.Ui.LS;

import duke.exceptions.DukeException;


// `public enum CommandType {` is defining an enumeration class named `CommandType`. An enumeration is a special type of
// class in Java that represents a fixed set of constants. In this case, `CommandType` defines a set of constants that
// represent the different types of commands that the Duke chatbot can handle. Each constant has a name (e.g. `BYE`,
// `LIST`, `MARK`, etc.) and an associated error message (stored as a `DukeException` object). The `CommandType` enum also
// provides a method `getErr()` to retrieve the error message associated with a particular command type.
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
    CommandType(String word, DukeException e) {
        this.word = word;
        this.e = e;
    }

    /**
     * The function returns a DukeException object.
     *
     * @return A DukeException object named "e" is being returned.
     */
    public DukeException getErr() {
        return this.e;
    }

    // The `toString()` method is overriding the default `toString()` method inherited from the `Object` class. It returns
    // a `String` representation of the `CommandType` object, which is the `word` field of the enum constant. This method
    // is useful when we need to convert an enum constant to a string representation.
    @Override
    public String toString() {
        return this.word;
    }
}
