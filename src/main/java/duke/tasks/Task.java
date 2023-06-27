package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class represents the state of the application for a task.
 */
public abstract class Task {

    // Task attributes
    protected String description;
    protected boolean isDone;

    /**
     * Construct a new task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Construct a new task with the given description and is done if it is not done before the given date.
     * @return
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Construct a new task with the given description and is done if it is not done before the given date and the given date is before the given date. If the given date is before the given date then the task is marked as done.     
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Construct a new task with the given description and is done if it if not done before the given date and the given date is before the given date. If the given date is before the given date then the task is marked as done.     
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Construct a new task with the given description and is done if it is not done before the given date and the given date is before the given date. If the given date is before the given date then the task is marked as done.      
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
     * Fixes the day and month of the given date.
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
     * Returns the date in the format MMM d yyyy.
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
     * String representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Text representation of the task.
     * @return
     */
    public abstract String toText();
}
