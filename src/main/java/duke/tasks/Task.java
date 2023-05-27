package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }
    public void markAsDone() {
        this.isDone = true;
    }
    public void unmarkAsDone() {
        this.isDone = false;
    }

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

    public abstract String toText();
}
