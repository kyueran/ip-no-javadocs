package duke.tasks;

import static duke.ui.Ui.LS;

import java.util.ArrayList;
import java.util.List;

// Creates a task list. Tasks are represented by ArrayLists of Task objects. The order of the ArrayLists is undefined
public class TaskList {

    // Returns a list of tasks that have been run. This is used to determine if a user is interested in an action
    private List<Task> tasks;

    // Clears the list of tasks. This is called when we have a new task
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    // Sets the list of tasks to be processed. This is used for debugging and to avoid having to re - create the list every time
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
    * Adds a task to the end of the list of tasks. This is useful for adding tasks that are part of a task group or other tasks that have already been added to the group.
    * 
    * @param t - The task to add to the list of tasks
    */
    public void addTask(Task t) {
        assert t != null : "The task being added is invalid!";
        tasks.add(t);
    }
    /**
    * Removes the task at the specified index. This does nothing if the index is out of range. Note that it is up to the caller to ensure that the task is removed before it is used in the list of tasks.
    * 
    * @param i - the index of the task to remove from the
    */
    public void removeTask(int i) {
        tasks.remove(i);
    }

    /**
    * Checks if a task is already in the list of tasks. This is used to prevent duplicates in the TaskList
    * 
    * @param newTask - The task to check.
    * 
    * @return True if the task is already in the list false otherwise. Note that duplicates are checked by name and the list is sorted
    */
    public boolean isDuplicate(Task newTask) {
        boolean isDup = false;
        for (Task t : this.tasks) {
            // if the task is a duplicate task
            if (newTask.toText().equals(t.toText())) {
                isDup = true;
            }
        }
        return isDup;
    }

    /**
    * Mark the task at index i as done. This is called by Task#markAsDone ( int )
    * 
    * @param i - index of the task to
    */
    public void markTask(int i) {
        Task t = tasks.get(i);
        t.markAsDone();
    }

    /**
    * Unmarks the task at index i as done. This is useful when you want to undo a task that was marked as done in the next call to #markTask ( int )
    * 
    * @param i - the index of the
    */
    public void unmarkTask(int i) {
        Task t = tasks.get(i);
        t.unmarkAsDone();
    }

    /**
    * Formats the list of tasks to a string. The format is " position. task ". It is used for debugging purposes and should not be confused with #print ( String ).
    * 
    * 
    * @return a string containing the list of tasks separated by " " and a space between each task and its position
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
    * Searches this list for tasks that contain the string s. This is used to generate a list of tasks that can be run by the command line
    * 
    * @param s - The string to search for
    * 
    * @return A list of tasks that match the string s or an empty string if no match is found. The list is separated by commas
    */
    public String findList(String s) {
        String matchList = "";
        for (Object t : this.tasks) {
            // Add a string to matchList.
            if (t.toString().contains(s)) {
                int pos = this.tasks.indexOf(t) + 1;
                matchList += pos + ". " + t + LS;
            }
        }
        return matchList.trim();
    }
    /**
    * Returns a message telling how many tasks are in the list. This is used to show the number of tasks in the list when there are more than one task per day.
    * 
    * 
    * @return a message telling how many tasks are in the list. This is used to show when there are more than one task per day
    */
    public String numTasksMsg() {
        return "Now you have " + this.tasks.size() + " tasks in the list.";
    }

    /**
    * Returns the text representation of the task at the given index. This is useful for debugging purposes. If you want to print the status of a task use Task#toText ()
    * 
    * @param i - the index of the task
    * 
    * @return the text representation of the
    */
    public String toText(int i) {
        Task t = tasks.get(i);
        return t.toText();
    }

    /**
    * Returns the string representation of the task at the specified index. This method is useful for debugging purposes.
    * 
    * @param i - the index of the task to get the string representation of
    * 
    * @return the string representation of the task at the specified index or null if the task does not exist or is
    */
    public String toString(int i) {
        Task t = tasks.get(i);
        return t.toString();
    }
}
