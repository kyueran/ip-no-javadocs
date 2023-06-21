package duke.tasks;

import static duke.ui.Ui.LS;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    /**
     * The TaskList function is a constructor that creates an empty ArrayList of Task objects.

     *
     *
     * @return An arraylist of task objects
     *
     * @docauthor Trelent
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * The TaskList function is a constructor that creates an ArrayList of Task objects.
     *
     *
     * @param ArrayList&lt;Task&gt; tasks Pass the tasks arraylist from the main class to this class
     *
     * @return An arraylist of task objects
     *
     * @docauthor Trelent
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * The addTask function adds a task to the list of tasks.
     *
     *
     * @param Task t Add a task to the list of tasks
     *
     * @return Void, as it does not return anything
     *
     * @docauthor Trelent
     */
    public void addTask(Task t) {
        assert t != null : "The task being added is invalid!";
        tasks.add(t);
    }

    /**
     * The removeTask function removes a task from the list of tasks.
     *
     *
     * @param int i Specify the index of the task to be removed
     *
     * @return A boolean value
     *
     * @docauthor Trelent
     */
    public void removeTask(int i) {
        tasks.remove(i);
    }

    /**
     * The isDuplicate function checks if the newTask is a duplicate of any existing tasks in the task list.
     *
     *
     * @param Task newTask Compare the new task to all of the tasks in the list
     *
     * @return A boolean value
     *
     * @docauthor Trelent
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
     * The markTask function marks a task as done.
     *
     *
     * @param int i Get the task from the list of tasks
     *
     * @return Nothing
     *
     * @docauthor Trelent
     */
    public void markTask(int i) {
        Task t = tasks.get(i);
        t.markAsDone();
    }

    /**
     * The unmarkTask function takes in an integer i and unmarks the task at index i as done.
     *
     *
     * @param int i Identify the task to be marked as done
     *
     * @return Void
     *
     * @docauthor Trelent
     */
    public void unmarkTask(int i) {
        Task t = tasks.get(i);
        t.unmarkAsDone();
    }

    /**
     * The formatList function takes the list of tasks and formats them into a numbered list.
     *
     *
     *
     * @return A string that is a list of all the tasks in the todolist object
     *
     * @docauthor Trelent
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
     * The findList function takes a string as an argument and returns a list of all tasks that contain the string.
     *
     *
     * @param String s Search for a specific task in the list
     *
     * @return A string containing all the tasks that contain
     *
     * @docauthor Trelent
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
     * The numTasksMsg function returns a string that tells the user how many tasks they have in their list.
     *
     *
     *
     * @return A string that tells the user how many tasks they have in their list
     *
     * @docauthor Trelent
     */
    public String numTasksMsg() {
        return "Now you have " + this.tasks.size() + " tasks in the list.";
    }

    /**
     * The toText function takes an integer i as a parameter and returns the text of the task at index i in tasks.
     *
     *
     * @param int i Get the task at index i in the tasks arraylist
     *
     * @return The task in text form
     *
     * @docauthor Trelent
     */
    public String toText(int i) {
        Task t = tasks.get(i);
        return t.toText();
    }

    /**
     * The toString function returns a string representation of the task at index i.
     *
     *
     * @param int i Get the task at index i in the tasks arraylist
     *
     * @return The string representation of the task at index i in the tasks array
     *
     * @docauthor Trelent
     */
    public String toString(int i) {
        Task t = tasks.get(i);
        return t.toString();
    }
}
