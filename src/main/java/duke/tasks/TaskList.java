package duke.tasks;

import static duke.ui.Ui.LS;

import java.util.ArrayList;
import java.util.List;

/**
 * The TaskList class represents a list of tasks and provides methods for adding, removing, marking, and formatting tasks.
 */
public class TaskList {

    // `private List<Task> tasks;` is declaring a private instance variable `tasks` of type `List<Task>`. This variable
    // will hold a list of `Task` objects.
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * This function adds a task to a list of tasks, but first checks that the task is not null.
     *
     * @param t t is a parameter of type Task, which represents the task being added to a collection of tasks.
     */
    public void addTask(Task t) {
        assert t != null : "The task being added is invalid!";
        tasks.add(t);
    }

    /**
     * This Java function removes a task from a list of tasks at a specified index.
     *
     * @param i The parameter "i" is an integer that represents the index of the task to be removed from the list of tasks.
     */
    public void removeTask(int i) {
        tasks.remove(i);
    }

    /**
     * The function checks if a new task is a duplicate of an existing task in a list.
     *
     * @param newTask The parameter `newTask` is an object of the `Task` class, which is being passed to the `isDuplicate`
     * method.
     * @return The method is returning a boolean value, which indicates whether the given `newTask` is a duplicate of any
     * of the tasks already present in the `tasks` list. If a duplicate is found, the method returns `true`, otherwise it
     * returns `false`.
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
     * The function marks a task as done based on its index in a list of tasks.
     *
     * @param i The parameter "i" is an integer representing the index of the task to be marked as done in the "tasks"
     * list.
     */
    public void markTask(int i) {
        Task t = tasks.get(i);
        t.markAsDone();
    }

    /**
     * The function unmarks a task as done based on its index in the list of tasks.
     *
     * @param i The parameter "i" is an integer representing the index of the task to be unmarked as done in the "tasks"
     * list.
     */
    public void unmarkTask(int i) {
        Task t = tasks.get(i);
        t.unmarkAsDone();
    }

    /**
     * This function formats a list of tasks by adding a number and a bullet point to each task.
     *
     * @return The method `formatList()` returns a formatted string representation of the list of tasks, where each task is
     * numbered and separated by a line separator.
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
     * The function finds and returns a list of tasks that contain a given string.
     *
     * @param s The parameter "s" is a String that is used to search for matching tasks in the "tasks" list. The method
     * iterates through the "tasks" list and checks if each task's string representation contains the search string "s". If
     * a match is found, the method adds the task's
     * @return The method `findList` is returning a string `matchList` which contains the list of tasks that match the
     * input string `s`. The list is formatted as a numbered list with each task on a new line. The `trim()` method is used
     * to remove any extra whitespace at the beginning or end of the string before returning it.
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
     * This function returns a message indicating the number of tasks in a list.
     *
     * @return The method `numTasksMsg()` is returning a string that includes the number of tasks in the list.
     * Specifically, it returns a string that says "Now you have X tasks in the list.", where X is the number of tasks in
     * the list.
     */
    public String numTasksMsg() {
        return "Now you have " + this.tasks.size() + " tasks in the list.";
    }

    /**
     * This function returns the text representation of a task object at a given index in a list.
     *
     * @param i The parameter "i" is an integer representing the index of the task in the "tasks" list that we want to
     * convert to text.
     * @return The method `toText(int i)` returns a string representation of the `Task` object at the specified index `i`
     * in the `tasks` list. It calls the `toText()` method of the `Task` class to get the string representation.
     */
    public String toText(int i) {
        Task t = tasks.get(i);
        return t.toText();
    }

    /**
     * This function returns a string representation of a Task object at a specified index in a list.
     *
     * @param i The parameter "i" is an integer representing the index of the task in the "tasks" list that we want to
     * convert to a string using the "toString()" method of the "Task" class.
     * @return The method is returning a string representation of the Task object at the specified index in the tasks list.
     * The string representation is obtained by calling the toString() method of the Task object.
     */
    public String toString(int i) {
        Task t = tasks.get(i);
        return t.toString();
    }
}
