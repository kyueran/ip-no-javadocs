package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * The Task function is a constructor for the Task class.
     * It takes in a String description and sets it as the description of the task,
     * and sets isDone to false.

     *
     * @param String description Set the description of the task
     *
     * @return A new task object with the given description
     *
     * @docauthor Trelent
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    /**
     * The getStatusIcon function returns a string that is either an X or a space.
     * If the task is done, it will return an X. Otherwise, it will return a space.

     *
     *
     * @return A string value
     *
     * @docauthor Trelent
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    /**
     * The markAsDone function sets the isDone variable to true.
     *
     *
     * @return Nothing, so it returns void
     *
     * @docauthor Trelent
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * The unmarkAsDone function sets the isDone variable to false.
     *
     *
     * @return Nothing
     *
     * @docauthor Trelent
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * The fixDateToParse function takes in a String date and returns the same date
     * but with the format changed from &quot;dd/mm/yyyy&quot; to &quot;yyyy-mm-dd&quot;. If there is an error,
     * it will return 0000-01-01. This function is used to fix dates that are not in
     * yy
     *
     * @param String date Get the date from the user
     *
     * @return A string in the format yyyy-mm-dd, where mm and dd are two digits
     *
     * @docauthor Trelent
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
     * The fixDayMonth function takes a String as an argument and returns a String.
     * The function checks if the length of the input is 1 or 2, and if it is, adds
     * a 0 to the beginning of it. If not, then null is returned. This function can be used
     * to fix day or month values that are less than 10 so they will have two digits instead
     * of one when printed out in date format (e.g., &quot;01&quot; instead of &quot;0&quot;). It also ensures that
     * days/months with two digits are left alone (e.g., &quot;12&quot; stays as
     *
     * @param String dayOrMonth Determine whether the day or month is being fixed
     *
     * @return A string
     *
     * @docauthor Trelent
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
     * The split function takes a string and splits it into two parts.
     * The first part is the date, which is then parsed to be in the format of &quot;MMM d yyyy&quot;.
     * If there are only two parts, then that means there was no time included in the original string.
     * If there were three or more parts, then we take everything after the second space as being part of
     * a time (which could include seconds). We add this to our new date and return it.

     *
     * @param String date Split the date into two parts, one for the date and one for the time
     *
     * @return An array of strings
     *
     * @docauthor Trelent
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
     * The toString function returns a string representation of the object.
     *
     *
     *
     * @return A string that contains the status icon and description of a task
     *
     * @docauthor Trelent
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public abstract String toText();
}
