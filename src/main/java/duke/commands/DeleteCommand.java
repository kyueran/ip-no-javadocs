package duke.commands;

import static duke.ui.Ui.LS;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    private int index;

    /**
     * The DeleteCommand function deletes the task at the index specified by the user.
     *
     *
     * @param String message Get the index of the task to be deleted
     *
     * @return The index of the message that is to be deleted
     *
     * @docauthor Trelent
     */
    public DeleteCommand(String message) {
        index = Integer.parseInt(message) - 1;
    }
    /**
     * The execute function removes a task from the TaskList and Storage.
     *
     *
     * @param TaskList tl Call the removetask method
     * @param Ui ui Display the task that has been removed
     * @param Storage s Delete the task from the storage
     *
     * @return A string that is displayed to the user
     *
     * @docauthor Trelent
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
     * The isExit function is a boolean function that returns false.
     *
     *
     *
     * @return False because the room is not an exit
     *
     * @docauthor Trelent
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
