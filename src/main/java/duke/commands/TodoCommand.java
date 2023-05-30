package duke.commands;

import static duke.ui.Ui.LS;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.ToDo;
import duke.ui.Ui;

/**
 * The TodoCommand class is a subclass of the Command class that adds a new ToDo task to a task list.
 */
public class TodoCommand extends Command {

    // `private String desc;` is declaring a private instance variable `desc` of type `String` in the `TodoCommand` class.
    // This variable is used to store the description of the ToDo task that will be added to the task list. The value of
    // `desc` is set in the constructor of the `TodoCommand` class.
    private String desc;

    public TodoCommand(String message) {
        this.desc = message;
    }

    // The `execute` method in the `TodoCommand` class is overriding the `execute` method in the `Command` class. It takes
    // in three parameters: a `TaskList` object `tl`, a `Ui` object `ui`, and a `Storage` object `s`.
    @Override
    public void execute(TaskList tl, Ui ui, Storage s) {
        ToDo t = new ToDo(desc);
        if (tl.isDuplicate(t)) {
            handleDuplicate(ui);
        } else {
            tl.addTask(t);
            s.addTask(t.toText());
            ui.display("Got it. I've added this task:" + LS + t + LS + tl.numTasksMsg());
        }
    }

    // The `public boolean isExit() {` method is overriding the `isExit()` method in the `Command` class. It returns a
    // boolean value of `false`, indicating that this command is not an exit command. The `isExit()` method is used to
    // determine whether the user wants to exit the program or not. If the method returns `true`, the program will exit. If
    // it returns `false`, the program will continue running.
    @Override
    public boolean isExit() {
        return false;
    }
}
