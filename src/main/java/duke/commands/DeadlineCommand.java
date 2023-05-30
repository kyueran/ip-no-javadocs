package duke.commands;

import static duke.ui.Ui.LS;

import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * The DeadlineCommand class is a subclass of the Command class that adds a new Deadline task to the task list.
 */
public class DeadlineCommand extends Command {

    // `private String desc;` is declaring a private instance variable `desc` of type `String` in the `DeadlineCommand`
    // class. This variable is used to store the description of the deadline task that will be added to the task list.
    private String desc;

    // `private String by;` is declaring a private instance variable `by` of type `String` in the `DeadlineCommand` class.
    // This variable is used to store the deadline of the deadline task that will be added to the task list.
    private String by;

    public DeadlineCommand(String desc, String by) {
        this.desc = desc;
        this.by = by;
    }

    // The `execute` method in the `DeadlineCommand` class is overriding the `execute` method in the `Command` class. It
    // takes in three parameters: a `TaskList` object `tl`, a `Ui` object `ui`, and a `Storage` object `s`.
    @Override
    public void execute(TaskList tl, Ui ui, Storage s) {
        Deadline t = new Deadline(this.desc, this.by);
        if (tl.isDuplicate(t)) {
            handleDuplicate(ui);
        } else {
            tl.addTask(t);
            s.addTask(t.toText());
            ui.display("Got it. I've added this task:" + LS + t + LS + tl.numTasksMsg());
        }
    }

    // The `isExit()` method is a method in the `DeadlineCommand` class that overrides the `isExit()` method in the
    // `Command` class. It returns a boolean value of `false`, indicating that this command is not an exit command. This
    // method is used to determine whether the user wants to exit the program or not. If the user enters an exit command,
    // the program will terminate.
    @Override
    public boolean isExit() {
        return false;
    }
}
