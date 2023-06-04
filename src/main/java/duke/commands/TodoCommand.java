package duke.commands;

import static duke.ui.Ui.LS;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.ToDo;
import duke.ui.Ui;

/**
 * Represents a command to add a todo task.
 */
public class TodoCommand extends Command {

    /**
     * The description of the todo task.
     */
    private String desc;

    /**
     * Constructs a TodoCommand object.
     * @param message
     */
    public TodoCommand(String message) {
        this.desc = message;
    }

    /**
     * Executes the todo command.
     * @param tl
     * @param ui
     * @param s
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
     * Exit status of the todo command.
     * @return
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
