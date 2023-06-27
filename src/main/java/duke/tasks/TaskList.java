package duke.tasks;

import static duke.ui.Ui.LS;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks.
 */
public class TaskList {

    // List of tasks
    private List<Task> tasks;

    /**
     * Constructs a new task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    /**
     * Constructs a new task list with the given list of tasks.
     * @param tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     * @param t
     */
    public void addTask(Task t) {
        assert t != null : "The task being added is invalid!";
        tasks.add(t);
    }

    /**
     * Removes a task from the list.
     */
    public void removeTask(int i) {
        tasks.remove(i);
    }

    /**
     * Checks if the given task is a duplicate.
     * @param newTask
     * @return
     */
    public boolean isDuplicate(Task newTask) {
        boolean isDup = false;
        for (Task t : this.tasks) {
            if (newTask.toText().equals(t.toText())) {
                isDup = true;
            }
        }
        return isDup;
    }

    /**
     * Marks a task as done.
     * @param i
     */
    public void markTask(int i) {
        Task t = tasks.get(i);
        t.markAsDone();
    }

    /**
     * Unmarks a task as not done.
     * @param i
     */
    public void unmarkTask(int i) {
        Task t = tasks.get(i);
        t.unmarkAsDone();
    }

    /**
     * Format the list.
     * @return
     */
    public String formatList() {
        String formattedList = "";
        for (Object t : this.tasks) {
            int pos = this.tasks.indexOf(t) + 1;
            formattedList += pos + ". " + t + LS;
        }
        return formattedList.trim();
    }

    /**
     * Find a task in the list.
     * @return
     */
    public String findList(String s) {
        String matchList = "";
        for (Object t : this.tasks) {
            if (t.toString().contains(s)) {
                int pos = this.tasks.indexOf(t) + 1;
                matchList += pos + ". " + t + LS;
            }
        }
        return matchList.trim();
    }

    /**
     * Returns the number of tasks in the list.
     * @return
     */
    public String numTasksMsg() {
        return "Now you have " + this.tasks.size() + " tasks in the list.";
    }

    /**
     * Text representation of the list.
     * @param i
     * @return
     */
    public String toText(int i) {
        Task t = tasks.get(i);
        return t.toText();
    }

    /**
     * String representation of the list.
     * @param i
     * @return
     */
    public String toString(int i) {
        Task t = tasks.get(i);
        return t.toString();
    }
}
