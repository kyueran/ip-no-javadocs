package duke.commands;

import static duke.ui.Ui.LS;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {

    /**
     * The index of the task to be deleted.
     */
    private int index;

    /**
     * Constructs a DeleteCommand object.
     * @param message
     */
    public DeleteCommand(String message) {
        index = Integer.parseInt(message) - 1;
    }

    /**
     * Executes the delete command.
     * @param tl
     * @param ui
     * @param s
     */
    @Override
    public void execute(TaskList tl, Ui ui, Storage s) {
        String taskText = tl.toText(this.index);
        String taskString = tl.toString(this.index);
        tl.removeTask(this.index);
        s.deleteTask(taskText);
        ui.display("Noted. I've removed this task:" + LS + taskString + LS + tl.numTasksMsg());
    }

    /**
     * Handles the case where the delete command is a duplicate.
     * @return
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
