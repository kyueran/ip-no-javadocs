package duke.commands;

import static duke.ui.Ui.LS;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.ToDo;
import duke.ui.Ui;

public class TodoCommand extends Command {
    private String desc;
    /**
     * The TodoCommand function is a constructor that takes in a String message and sets the desc variable to be equal to it.
     *
     *
     * @param String message Set the description of the todo

    }
     *
     * @return A todocommand object
     *
     * @docauthor Trelent
     */
    public TodoCommand(String message) {
        this.desc = message;
    }

    /**
     * The execute function takes in a TaskList, Ui and Storage object.
     * It creates a new ToDo task with the description given by the user.
     * If this task is not a duplicate of any other tasks in the list, it adds it to
     * both the TaskList and Storage objects. Otherwise, it displays an error message
     * to inform that there is already such a task on record. In either case, it also
     * displays how many tasks are currently on record after adding or not adding this one.


     *
     * @param TaskList tl Add the new task to the list
     * @param Ui ui Display the message to the user
     * @param Storage s Access the storage class
     *
     * @return Void
     *
     * @docauthor Trelent
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
     * The isExit function is a boolean function that returns false.
     *
     *
     *
     * @return False
     *
     * @docauthor Trelent
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
