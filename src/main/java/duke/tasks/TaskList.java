package duke.tasks;

import static duke.ui.Ui.LS;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Task list.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Instantiates a new Task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Instantiates a new Task list.
     *
     * @param tasks the tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Add task.
     *
     * @param t the t
     */
    public void addTask(Task t) {
        assert t != null : "The task being added is invalid!";
        tasks.add(t);
    }

    /**
     * Remove task.
     *
     * @param i the
     */
    public void removeTask(int i) {
        tasks.remove(i);
    }

    /**
     * Is duplicate boolean.
     *
     * @param newTask the new task
     * @return the boolean
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
     * Mark task.
     *
     * @param i the
     */
    public void markTask(int i) {
        Task t = tasks.get(i);
        t.markAsDone();
    }

    /**
     * Unmark task.
     *
     * @param i the
     */
    public void unmarkTask(int i) {
        Task t = tasks.get(i);
        t.unmarkAsDone();
    }

    /**
     * Format list string.
     *
     * @return the string
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
     * Find list string.
     *
     * @param s the s
     * @return the string
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
     * Num tasks msg string.
     *
     * @return the string
     */
    public String numTasksMsg() {
        return "Now you have " + this.tasks.size() + " tasks in the list.";
    }

    /**
     * To text string.
     *
     * @param i the
     * @return the string
     */
    public String toText(int i) {
        Task t = tasks.get(i);
        return t.toText();
    }

    /**
     * To string string.
     *
     * @param i the
     * @return the string
     */
    public String toString(int i) {
        Task t = tasks.get(i);
        return t.toString();
    }
}
