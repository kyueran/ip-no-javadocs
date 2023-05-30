package duke.tasks;

import static duke.ui.Ui.LS;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks.
 */
public class TaskList {

    /**
     * The list of tasks.
     */
    private List<Task> tasks;

    /**
     * Constructs a TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList object.
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
     * @param i
     */
    public void removeTask(int i) {
        tasks.remove(i);
    }

    /**
     * Returns true if the task is a duplicate.
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
     * Marks the task at the specified index as done.
     * @param i
     */
    public void markTask(int i) {
        Task t = tasks.get(i);
        t.markAsDone();
    }

    /**
     * Unmarks the task at the specified index as done.
     * @param i
     */
    public void unmarkTask(int i) {
        Task t = tasks.get(i);
        t.unmarkAsDone();
    }

    /**
     * Returns a list of tasks.
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
     * Returns a list of tasks that match the keyword.
     * @param s
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
     * Returns the list of tasks in the format for saving.
     * @return
     */
    public String toText(int i) {
        Task t = tasks.get(i);
        return t.toText();
    }

    /**
     * Returns the list of tasks in the format for printing.
     * @return
     */
    public String toString(int i) {
        Task t = tasks.get(i);
        return t.toString();
    }
}
