package duke.tasks;

import static duke.ui.Ui.LS;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    public void addTask(Task t) {
        assert t != null : "The task being added is invalid!";
        tasks.add(t);
    }
    public void removeTask(int i) {
        tasks.remove(i);
    }

    public boolean isDuplicate(Task newTask) {
        boolean isDup = false;
        for (Task t : this.tasks) {
            if (newTask.toText().equals(t.toText())) {
                isDup = true;
            }
        }
        return isDup;
    }

    public void markTask(int i) {
        Task t = tasks.get(i);
        t.markAsDone();
    }

    public void unmarkTask(int i) {
        Task t = tasks.get(i);
        t.unmarkAsDone();
    }

    public String formatList() {
        String formattedList = "";
        for (Object t : this.tasks) {
            int pos = this.tasks.indexOf(t) + 1;
            formattedList += pos + ". " + t + LS;
        }
        return formattedList.trim();
    }

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
    public String numTasksMsg() {
        return "Now you have " + this.tasks.size() + " tasks in the list.";
    }

    public String toText(int i) {
        Task t = tasks.get(i);
        return t.toText();
    }

    public String toString(int i) {
        Task t = tasks.get(i);
        return t.toString();
    }
}
