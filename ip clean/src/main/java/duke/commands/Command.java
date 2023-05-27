package duke.commands;

import static duke.ui.Ui.LS;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tl, Ui ui, Storage s);
    public abstract boolean isExit();
    public void handleDuplicate(Ui ui) {
        ui.display("I did not add this task as it is a duplicate!");
    }
}
