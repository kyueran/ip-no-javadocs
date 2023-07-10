package duke;

import gui.DialogBox;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class Duke {

    // Returns the storage to use for this file. This is a private method so it can be shared by subclasses
    private Storage storage;

    // Returns the list of tasks to be run. This is a private method so it can be shared
    private TaskList tasks;

    // Called when user presses enter in the UI. This is a no - op
    private Ui ui;

    public Duke() {}

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
    * Runs the program until the user enters a quit command. This method is blocking so should be called in a seperate thread
    */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        // Executes the tasks in the background.
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
    * Parses and executes a command. This is the entry point for command execution. It will return the response from the command and show an error if there is an error.
    * 
    * @param input - The command to parse and execute. Must be parsable by Parser.
    * 
    * @return The response from the command as a string or null if there is an error in the command or the user chose to exit
    */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            return ui.getResponse();
        } catch (DukeException e) {
            return ui.showError((e.getMessage()));
        }
    }
}
