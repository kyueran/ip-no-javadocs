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
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * The Duke function is a constructor for the Duke class.
     * It takes no parameters and returns nothing.

     *
     *
     * @return An object of type duke
     *
     * @docauthor Trelent
     */
    public Duke() {}

    /**
     * The Duke function is the main function of Duke. It takes in a String array
     * and returns nothing. The function first checks if there are any arguments,
     * then it will check for the command given by the user and execute that
     * command accordingly. If no commands are given, it will show an error message
     * to inform users that they have not entered any commands or arguments. If an
     * invalid command is entered, it will also show an error message to inform users
     * that their input was invalid and what they should do instead (i.e., enter a valid command).


     *
     * @param String filePath Load the tasks from a file
     *
     * @return A new duke object
     *
     * @docauthor Trelent
     */
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
     * The run function is the main function of Duke. It runs a loop that reads in user input, parses it into a command, and executes the command.
     * The loop ends when an exit command is entered by the user.

     *
     *
     * @return Nothing
     *
     * @docauthor Trelent
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
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
     * The getResponse function takes in a String input and returns a String output.
     * It first parses the input into a Command object, then executes the command on tasks, ui and storage.
     * Finally it returns the response from ui as an output string.

     *
     * @param String input Pass the user input to the parser
     *
     * @return The response from the ui, which is a string
     *
     * @docauthor Trelent
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
