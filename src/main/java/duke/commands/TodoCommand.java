package duke.commands;

import static duke.ui.Ui.LS;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.ToDo;
import duke.ui.Ui;

/**
 * Adds a task to the task list.
 */
public class TodoCommand extends Command {

    // Description of the task.
    private String desc;

    /**
     * Construct the task list command with the description of the task.
     * @param message
     */
    public TodoCommand(String message) {
        this.desc = message;
    }

    /**
     * Construct the task list command with the description of the task and the index of the task.
     */
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

    /**
     * Checks if the TodoCommand is an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
