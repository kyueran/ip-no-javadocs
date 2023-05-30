package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Task class is an abstract class that defines methods for marking tasks as done, fixing and parsing dates, and
 * converting tasks to text.
 */
public abstract class Task {

    // `protected String description;` is declaring a protected instance variable `description` of type `String` in the
    // `Task` class. This variable is used to store the description of the task. The `protected` access modifier allows the
    // variable to be accessed by subclasses of `Task`.
    protected String description;


    // `protected boolean isDone;` is declaring a protected instance variable `isDone` of type `boolean` in the `Task`
    // class. This variable is used to store the completion status of the task. The `protected` access modifier allows the
    // variable to be accessed by subclasses of `Task`.
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * The function returns "X" if a task is done, and " " (a space) if it is not done.
     *
     * @return The method `getStatusIcon()` returns a string that represents the status of a task. If the task is done, it
     * returns the string "X", otherwise it returns a space character " ".
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    /**
     * The function "markAsDone" sets the boolean variable "isDone" to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * The function unmarks a task as done by setting its isDone attribute to false.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * This Java function takes a date string in the format "dd/mm/yyyy" and returns it in the format "yyyy-mm-dd", or
     * "0000-01-01" if the input is invalid.
     *
     * @param date The input date string in the format "dd/MM/yyyy".
     * @return The method is returning a string in the format "yyyy-MM-dd" representing a fixed version of the input date
     * string in the format "dd/MM/yyyy". If the input date string is not in the correct format or if the day or month
     * values are invalid, the method returns a default error string "0000-01-01".
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
     * The function adds a leading zero to a string if it has only one character.
     *
     * @param dayOrMonth The parameter `dayOrMonth` is a String representing either a day or a month in a date format.
     * @return A String value is being returned.
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
     * The function splits a date string into two parts, parses the first part as a LocalDate, and returns a formatted
     * string with the date and time (if provided).
     *
     * @param date
     * @return The method is returning a formatted date string. If the input `date` contains a time component, it will
     * return the date in the format "MMM d yyyy, HH:mm:ss" (e.g. "Jan 1 2022, 14:30:00"). If the input `date` does not
     * contain a time component, it will return the date in the format "MMM d
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

    // The `toString()` method is overriding the default implementation of the `toString()` method inherited from the
    // `Object` class. It returns a string representation of the `Task` object, which includes the completion status of the
    // task and its description. The returned string is in the format "[X] description" if the task is done, and "[ ]
    // description" if the task is not done.
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * The function "toText" is an abstract method that returns a string.
     *
     * @return A String is being returned. The method signature indicates that the method returns a String. However, since
     * the method is abstract, it does not have an implementation in this class and must be implemented by any concrete
     * subclass.
     */
    public abstract String toText();
}
