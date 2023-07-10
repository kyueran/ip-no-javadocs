package duke.commands;

import static duke.ui.Ui.LS;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.ToDo;
import duke.ui.Ui;

// This is where you'll get the TodoCommand. It's used to set the properties of the command
public class TodoCommand extends Command {

    // This is called when we are about to create a class. The description is passed to the constructor
    private String desc;

    // Sets the description of the TodoCommand. This is used to show the help
    public TodoCommand(String message) {
        this.desc = message;
    }
    /**
    * Creates a ToDo and adds it to the TaskList. If the ToDo is a duplicate it will be handled by handleDuplicate ()
    * 
    * @param tl - The TaskList to add the ToDo to
    * @param ui - The Ui to display the message to. Can be null
    * @param s - The Storage to store the
    */
    @Override
    public void execute(TaskList tl, Ui ui, Storage s) {
        ToDo t = new ToDo(desc);
        // Add a task to the list of tasks.
        if (tl.isDuplicate(t)) {
            handleDuplicate(ui);
        } else {
            tl.addTask(t);
            s.addTask(t.toText());
            ui.display("Got it. I've added this task:" + LS + t + LS + tl.numTasksMsg());
        }
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
