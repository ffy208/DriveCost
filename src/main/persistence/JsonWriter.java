package persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import model.User;
import model.UserDatabase;
import org.json.JSONObject;

// Represents a writer that writes JSON representation of user to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        this.writer = new PrintWriter(new File(this.destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of user to file
    public void write(User user) {
        JSONObject json = user.toJson();
        this.saveToFile(json.toString(4));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        this.writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    protected void saveToFile(String json) {
        this.writer.print(json);
    }
}
