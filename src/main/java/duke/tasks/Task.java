package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The type Task.
 */
public abstract class Task {
    /**
     * The Description.
     */
    protected String description;
    /**
     * The Is done.
     */
    protected boolean isDone;

    /**
     * Instantiates a new Task.
     *
     * @param description the description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets status icon.
     *
     * @return the status icon
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Mark as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmark as done.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Fix date to parse string.
     *
     * @param date the date
     * @return the string
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
     * Split string.
     *
     * @param date the date
     * @return the string
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

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * To text string.
     *
     * @return the string
     */
    public abstract String toText();
}
