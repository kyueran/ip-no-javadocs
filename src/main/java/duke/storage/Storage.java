package duke.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

public class Storage {
    private File storageFile;
    public Storage(String filePath) {
        this.storageFile = makeFile(filePath);
    }

    /**
    * Creates a file at the given path. The parent directories of the file will be created if they do not exist.
    * 
    * @param filePath - Path to the file to create. Must not be null.
    * 
    * @return File that was created or null if an error occurred during creation ( for example if the file already exists
    */
    private File makeFile(String filePath) {
        File f = new File(filePath);
        f.getParentFile().mkdirs();
        try {
            f.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return f;
    }

    //Solution below adapted from https://stackoverflow.com/questions/1377279/find-a-line-in-a-file-and-remove-it

    /**
    * Deletes a line from the storage file. This method is used to delete a line that is no longer used in the data file
    * 
    * @param line - The line to be
    */
    private void deleteLine(String line) {
        assert this.storageFile.exists() : "File to be deleted from does not exist!";
        File tempFile = new File("temp.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.storageFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String rmLine = line;
            String currLine;
            boolean done = false;
            // Reads the next line from the input stream and writes it to the writer.
            while (true) {
                currLine = reader.readLine();
                // This method will break the current line if it is not already in the current line.
                if (currLine == null) {
                    break;
                }
                String trimmedLine = currLine.trim();
                // If this is a trimmed line and the line is equal to rmLine then continue.
                if (trimmedLine.equals(rmLine) && !done) {
                    done = true;
                    continue;
                }
                writer.write(currLine + "\n");
            }
            writer.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tempFile.renameTo(this.storageFile);
    }

    /**
    * Modify a line in storage file. This method is used to modify the content of an existing line in the storage file
    * 
    * @param line - the line to be modified
    * @param newLine - the new line to be added to the
    */
    private void modifyLine(String line, String newLine) {
        assert this.storageFile.exists() : "File to be modified does not exist!";
        File tempFile = new File("temp.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.storageFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String lineToBeModified = line;
            String currLine;
            boolean done = false;
            // Reads the next line from the input stream and writes it to the writer.
            while (true) {
                currLine = reader.readLine();
                // This method will break the current line if it is not already in the current line.
                if (currLine == null) {
                    break;
                }
                String trimmedLine = currLine.trim();
                // Write the line to the writer.
                if (trimmedLine.equals(lineToBeModified) && !done) {
                    done = true;
                    writer.write(newLine + "\n");
                } else {
                    writer.write(currLine + "\n");
                }
            }
            writer.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tempFile.renameTo(this.storageFile);
    }

    /**
    * Appends text to the storage file. This method is used to append a line of text to the end of the file
    * 
    * @param text - The text to be
    */
    private void appendToFile(String text) {
        assert this.storageFile.exists() : "File to be written to does not exist!";
        try {
            FileWriter fw = new FileWriter(this.storageFile, true); // create a FileWriter in append mode
            fw.write(text + "\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
    * Loads the list of tasks from the storage file. If the storage file does not exist an empty list is returned.
    * 
    * 
    * @return the list of tasks loaded from the storage file or an empty list if there are no tasks to load
    */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> userTasks = new ArrayList<>();
        // Returns the user tasks that are currently running.
        if (!this.storageFile.exists()) {
            return userTasks;
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.storageFile));
            String currLine;
            // Reads the next line from the reader and parses the task data.
            while (true) {
                currLine = reader.readLine();
                // This method will break the current line if it is not already in the current line.
                if (currLine == null) {
                    break;
                }
                parseTaskFromFile(userTasks, currLine);
            }
            reader.close();
        } catch (IOException e) {
            throw new DukeException("Error loading file");
        }
        return userTasks;
    }

    /**
    * Parses a line from the log file and adds it to the list of tasks. This is used to parse the status of a task and also to determine if it is done or not
    * 
    * @param userTasks - List to add task to
    * @param currLine - String to parse from the log file as
    */
    private static void parseTaskFromFile(ArrayList<Task> userTasks, String currLine) {
        String[] fields = currLine.split(Pattern.quote(" | "));
        Task t;
        // Create a task object from the fields of the task.
        if (fields[0].equals("T")) {
            t = new ToDo(fields[2]);
        // Create a task object from the fields of the task.
        } else if (fields[0].equals("D")) {
            t = new Deadline(fields[2], fields[3]);
        // This method is used to create an event object
        } else if (fields[0].equals("E")) {
            t = new Event(fields[2], fields[3], fields[4]);
        } else {
            //Do not add invalid task to userTasks
            return;
        }
        // Mark the t as done.
        if (fields[1].equals("1")) {
            t.markAsDone();
        }
        userTasks.add(t);
    }

    /**
    * Adds a task to the file. This is a convenience method for #appendToFile ( String ).
    * 
    * @param taskText - the text of the task to be added
    */
    public void addTask(String taskText) {
        appendToFile(taskText);
    }

    /**
    * Modifies the text that is in the task. This is a convenience method that calls #modifyLine ( String String )
    * 
    * @param oldText - the old text to be replaced
    * @param newText - the new text to replace the old text
    */
    public void modifyTask(String oldText, String newText) {
        modifyLine(oldText, newText);
    }

    /**
    * Deletes the task with the given text. Does nothing if the task doesn't exist. This is equivalent to pressing delete in the statusbar
    * 
    * @param taskText - text of the task to
    */
    public void deleteTask(String taskText) {
        deleteLine(taskText);
    }
}
