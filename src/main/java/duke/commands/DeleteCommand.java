package duke.commands;

import static duke.ui.Ui.LS;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

// Creates a delete command that deletes a row from the database. If the row doesn't exist it will be created
public class DeleteCommand extends Command {

    // Returns the index of the element to be processed. This is used to handle cases where the element is a subelement of another
    private int index;

    // Sets the index to the given message. This is used to determine if there is a conflict
    public DeleteCommand(String message) {
        index = Integer.parseInt(message) - 1;
    }

    /**
    * Removes the task from the TaskList and displays a message to the user. This is called when the user presses the delete button
    * 
    * @param tl - The TaskList to be removed
    * @param ui - The Ui to be used
    * @param s - The Storage to be used ( for debugging purposes
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
    * Returns true if the program should exit. This is used to prevent infinite loops in Jitsi and other programming languages that are in an unreliable state.
    * 
    * 
    * @return true if the program should exit false otherwise. Note that false is always returned for a program that doesn't exit
    */
    @Override
    public boolean isExit() {
        return false;
    }
}
