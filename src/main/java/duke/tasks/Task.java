package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// The task to run. If this method returns null it means that the task is a no - op
public abstract class Task {
    
    // Override to provide a description of the object. Subclasses should override this if they need more information
    protected String description;

    // Called when the task is done. This is the place to do things like check if there are any tasks to be done
    protected boolean isDone;

    // Sets the description and isDone fields of this task. This method is called by #create ()
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
    * Returns the status icon of the task. This icon is used to display the status of the task and can be clicked to change it.
    * 
    * 
    * @return String representing the status icon of the task ( " X " or " Y " ) as defined in ITask
    */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    /**
    * Mark this task as done. This is called by #doWork () to indicate that the task has finished
    */
    public void markAsDone() {
        this.isDone = true;
    }
    
    /**
    * Marks this task as done. This is useful when you want to know when the task is done or not
    */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
    * Takes a string in the format YYYY / MM / DD and attempts to parse it into a date.
    * 
    * @param date - String in the format YYYY / MM / DD
    * 
    * @return String with the date in the format YYYY - MM - DD or error if there was a problem parsing
    */
    public String fixDateToParse(String date) {
        String error = "0000-01-01";
        String[] arr = date.split("/");
        String year;
        String month;
        String day;
        // check if the array length is 3
        if (arr.length != 3) {
            return error;
        }

        year = arr[2];
        month = fixDayMonth(arr[1]);
        day = fixDayMonth(arr[0]);
        // Returns the error if the month or day is null.
        if (month == null || day == null) {
            System.out.println("I RAN");
            return error;
        }
        return year + "-" + month + "-" + day;
    }

    /**
    * If dayOrMonth is 1 or 2 characters it adds 0 to it. Otherwise it returns null.
    * 
    * @param dayOrMonth - the day or month to fix.
    * 
    * @return the fixed day or month or null if it's not valid for the date format ( days and months
    */
    private String fixDayMonth(String dayOrMonth) {
        String result;
        // Returns the day of month.
        if (dayOrMonth.length() == 1) {
            result = "0" + dayOrMonth;
        // Returns the day or month of the day or null if the day is not 2 characters long.
        } else if (dayOrMonth.length() == 2) {
            result = dayOrMonth;
        } else {
            result = null;
        }
        return result;
    }

    /**
    * Splits a date into two parts. The first part is the date and the second part is the date
    * 
    * @param date - the date to be split
    * 
    * @return the date with the date split into two parts ( MMM d yyyy ) or two parts ( MMM d
    */
    public String split(String date) {
        String[] arr = date.split(" ", 2);
        String stringToParse = fixDateToParse(arr[0]);
        LocalDate d = LocalDate.parse(stringToParse);
        // Format the date in the format MMMM d yyyy
        if (arr.length == 1) {
            return d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            return d.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ", " + arr[1];
        }
    }

    /**
    * Returns a String representation of this Status. The String will be in the format [ StatusIcon ] Description
    * 
    * 
    * @return a String representation of this
    */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
    * Returns the text representation of this message. This method is called by Message#toString () to convert the message to a textual representation.
    * 
    * 
    * @return the text representation of this message or null if there is no text representation to be returned for the message
    */
    public abstract String toText();
}
