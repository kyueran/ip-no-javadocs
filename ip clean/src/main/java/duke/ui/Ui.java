package duke.ui;

import java.util.Scanner;

public class Ui {
    public static final String LS = System.lineSeparator();
    public static final String DIVIDER = "---------------------------------------------------------";
    public static final String WARNING = "************************************";
    public static final String DATE_TIME_FORMAT = "DD/MM/YYYY Time";
    private Scanner allCommands = new Scanner(System.in);

    private String response = showWelcome();

    public String formatString(String input, String type) {
        return type + LS + input + LS + type;
    }

    public String showWelcome() {
        String intro = "Hello! I'm Gerty, a cool Task Manager." + LS + "What can I do for you?";
        String usages = "HERE ARE THE TASKS YOU CAN RECORD:" + LS + DIVIDER + LS
                + "todo <description>" + LS + LS
                + "deadline <description> /by <" + DATE_TIME_FORMAT + ">" + LS + LS
                + "event <description> /from <" + DATE_TIME_FORMAT + "> /to <" + DATE_TIME_FORMAT + ">";

        return this.formatString(intro + LS + LS + usages, DIVIDER);
    }

    public String readCommand() {
        return this.allCommands.nextLine();
    }

    public void showLine() {
        System.out.println(DIVIDER);
    }

    public void showLoadingError() {
        String failToLoad = "I failed to load your tasks from my storage :(" + LS
                + "Let's record your tasks from scratch!";
        this.response = formatString(failToLoad, WARNING);
    }

    public String showError(String err) {
        return this.formatString(err, WARNING);
    }

    public void display(String message) {
        assert message != null : "Message to be displayed is invalid!";
        this.response = message;
    }

    public String getResponse() {
        return this.response;
    }
}
