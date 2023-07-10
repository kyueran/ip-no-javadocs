package duke.parser;

import java.util.regex.Pattern;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.IncorrectCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.TodoCommand;
import duke.commands.UnmarkCommand;
import duke.enums.CommandType;
import duke.exceptions.DukeException;


public class Parser {

    /**
    * Parses a command and returns a Command. This method is used to parse commands that are sent to Duke.
    * 
    * @param fullCommand - The command to parse. Must be in the format " BYE LIST MARK UNMARK DELETE ".
    * 
    * @return The parsed Command object. Never null but may be null if the command is invalid or not supported by the Duke
    */
    public static Command parse(String fullCommand) throws DukeException {
        String[] arr = fullCommand.split(" ", 2);
        String commandType = arr[0].toLowerCase();
        // Returns a new instance of the command.
        if (commandType.equals(CommandType.BYE.toString())) {
            return new ByeCommand();
        // Returns a new instance of the command.
        } else if (commandType.equals(CommandType.LIST.toString())) {
            return new ListCommand();
        // Returns a new instance of the command.
        } else if (commandType.equals(CommandType.MARK.toString())) {
            // Mark command is not a single item.
            if (arr.length == 1) {
                throw CommandType.MARK.getErr();
            }
            return new MarkCommand(arr[1]);
        // Returns a new instance of the command.
        } else if (commandType.equals(CommandType.UNMARK.toString())) {
            // check if there is only one item in the array
            if (arr.length == 1) {
                throw CommandType.UNMARK.getErr();
            }
            return new UnmarkCommand(arr[1]);
        // Returns a Command object for the given command type.
        } else if (commandType.equals(CommandType.DELETE.toString())) {
            // DELETE command. If the array is empty throw CommandType. DELETE.
            if (arr.length == 1) {
                throw CommandType.DELETE.getErr();
            }
            return new DeleteCommand(arr[1]);
        // Returns a command object based on the command type.
        } else if (commandType.equals(CommandType.FIND.toString())) {
            // Throws an error if the array is empty.
            if (arr.length == 1) {
                throw CommandType.FIND.getErr();
            }
            return new FindCommand(arr[1]);
        // Returns a command object.
        } else if (commandType.equals(CommandType.TODO.toString())) {
            // Check if there is only one element in the array.
            if (arr.length == 1) {
                throw CommandType.TODO.getErr();
            }
            return new TodoCommand(arr[1]);
        // Returns a new command object.
        } else if (commandType.equals(CommandType.DEADLINE.toString())) {
            // if arr. length 1 throw CommandType. DEADLINE. getError
            if (arr.length == 1) {
                throw CommandType.DEADLINE.getErr();
            }
            return prepareDeadline(arr[1]);
        // Returns a command object for the event.
        } else if (commandType.equals(CommandType.EVENT.toString())) {
            // Throws an event if the array is empty.
            if (arr.length == 1) {
                throw CommandType.EVENT.getErr();
            }
            return prepareEvent(arr[1]);
        } else {
            return new IncorrectCommand();
        }
    }

    /**
    * Parses and prepares a DeadlineCommand. This method is used to parse the message received from Duke's daemon and return a DeadlineCommand with the description and by fields filled in.
    * 
    * @param message - The message received from the daemon. Must be in the format " description by ".
    * 
    * @return The deadline command that was parsed from the message and passed to #execute ( Command ) or null if there was no deadline
    */
    private static DeadlineCommand prepareDeadline(String message) throws DukeException {
        Pattern p = Pattern.compile("/by");
        String[] temp = p.split(message);
        // if temp. length 2
        if (temp.length < 2) {
            throw CommandType.DEADLINE.getErr();
        }
        String desc = temp[0].trim();
        String by = temp[1].trim();
        return new DeadlineCommand(desc, by);
    }

    /**
    * Parses and prepares an event command. The message should be of the form " from to " where from and to are separated by spaces
    * 
    * @param message - The message to be parsed
    * 
    * @return The parsed and prepared event command for the given message or null if the message is not valid for the
    */
    private static EventCommand prepareEvent(String message) throws DukeException {
        Pattern p1 = Pattern.compile("/from");
        String[] temp1 = p1.split(message);
        // if temp1. length 2
        if (temp1.length < 2) {
            throw CommandType.EVENT.getErr();
        }
        String desc = temp1[0].trim();
        Pattern p2 = Pattern.compile("/to");
        String[] temp2 = p2.split(temp1[1]);
        // if temp2. length 2
        if (temp2.length < 2) {
            throw CommandType.EVENT.getErr();
        }
        String from = temp2[0].trim();
        String to = temp2[1].trim();
        return new EventCommand(desc, from, to);
    }
}
