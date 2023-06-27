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

    private void deleteLine(String line) {
        assert this.storageFile.exists() : "File to be deleted from does not exist!";
        File tempFile = new File("temp.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.storageFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String rmLine = line;
            String currLine;
            boolean done = false;
            while (true) {
                currLine = reader.readLine();
                if (currLine == null) {
                    break;
                }
                String trimmedLine = currLine.trim();
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
     * Returns the number of lines in the file.
     * @param line
     * @param newLine
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
            while (true) {
                currLine = reader.readLine();
                if (currLine == null) {
                    break;
                }
                String trimmedLine = currLine.trim();
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
     * Adds a task to the file.
     * @param task
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

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> userTasks = new ArrayList<>();
        if (!this.storageFile.exists()) {
            return userTasks;
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.storageFile));
            String currLine;
            while (true) {
                currLine = reader.readLine();
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

    private static void parseTaskFromFile(ArrayList<Task> userTasks, String currLine) {
        String[] fields = currLine.split(Pattern.quote(" | "));
        Task t;
        if (fields[0].equals("T")) {
            t = new ToDo(fields[2]);
        } else if (fields[0].equals("D")) {
            t = new Deadline(fields[2], fields[3]);
        } else if (fields[0].equals("E")) {
            t = new Event(fields[2], fields[3], fields[4]);
        } else {
            //Do not add invalid task to userTasks
            return;
        }
        if (fields[1].equals("1")) {
            t.markAsDone();
        }
        userTasks.add(t);
    }

    public void addTask(String taskText) {
        appendToFile(taskText);
    }

    public void modifyTask(String oldText, String newText) {
        modifyLine(oldText, newText);
    }

    public void deleteTask(String taskText) {
        deleteLine(taskText);
    }
}
