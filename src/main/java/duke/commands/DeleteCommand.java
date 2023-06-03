package duke.commands;

import static duke.ui.Ui.LS;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * The DeleteCommand class is a subclass of the Command class that represents a command to delete a task from a task list.
 */
public class DeleteCommand extends Command {

    // `private int index;` is declaring a private instance variable `index` of type `int` in the `DeleteCommand` class.
    // This variable is used to store the index of the task to be deleted from the task list. It is initialized in the
    // constructor of the `DeleteCommand` class by parsing the user input message to an integer and subtracting 1 to
    // convert it to a zero-based index.
    private int index;

    public DeleteCommand(String message) {
        index = Integer.parseInt(message) - 1;
    }

    // The `execute` method in the `DeleteCommand` class is overriding the `execute` method in the `Command` class. It
    // takes in a `TaskList` object, a `Ui` object, and a `Storage` object as parameters. When called, it removes the task
    // at the specified index from the `TaskList`, deletes the task from the `Storage`, and displays a message to the user
    // indicating that the task has been removed.
    @Override
    public void execute(TaskList tl, Ui ui, Storage s) {
        String taskText = tl.toText(this.index);
        String taskString = tl.toString(this.index);
        tl.removeTask(this.index);
        s.deleteTask(taskText);
        ui.display("Noted. I've removed this task:" + LS + taskString + LS + tl.numTasksMsg());
    }

    // The `isExit()` method is a method in the `DeleteCommand` class that overrides the `isExit()` method in the `Command`
    // class. It returns a boolean value indicating whether or not the command is an exit command. In this case, it returns
    // `false`, indicating that the `DeleteCommand` is not an exit command.
    @Override
    public boolean isExit() {
        return false;
    }
}
