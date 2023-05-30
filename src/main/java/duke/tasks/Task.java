package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task.
 */
public abstract class Task {

    /**
     * The description of the task.
     */
    protected String description;

    /**
     * Whether the task is done.
     */
    protected boolean isDone;

    /**
     * Constructs a Task object.
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * @return
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as done.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Returns the date in the format of yyyy-MM-dd.
     * @param date
     * @return
     */
    public String fixDateToParse(String date) {
        String error = "0000-01-01";
        String[] arr = date.split("/");
        String year;
        String month;
        String day;
        if (arr.length != 3) {
            return error;
        }

        year = arr[2];
        month = fixDayMonth(arr[1]);
        day = fixDayMonth(arr[0]);
        if (month == null || day == null) {
            System.out.println("I RAN");
            return error;
        }
        return year + "-" + month + "-" + day;
    }

    /**
     * Returns the day or month in the format of MM or DD.
     * @param dayOrMonth
     * @return
     */
    private String fixDayMonth(String dayOrMonth) {
        String result;
        if (dayOrMonth.length() == 1) {
            result = "0" + dayOrMonth;
        } else if (dayOrMonth.length() == 2) {
            result = dayOrMonth;
        } else {
            result = null;
        }
        return result;
    }

    /**
     * Returns the date in the format of MMM d yyyy.
     * @param date
     * @return
     */
    public String split(String date) {
        String[] arr = date.split(" ", 2);
        String stringToParse = fixDateToParse(arr[0]);
        LocalDate d = LocalDate.parse(stringToParse);
        if (arr.length == 1) {
            return d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            return d.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ", " + arr[1];
        }
    }

    /**
     * Returns the task in the format of string.
     * @return
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns the task in the format of text.
     * @return
     */
    public abstract String toText();
}
