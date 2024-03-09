package persistence;

import model.UserDatabase;
import org.json.JSONObject;

// Represents a writer that writes JSON representation of UserDatabase to file
public class JsonDatabaseWriter extends JsonWriter {

    // EFFECTS: constructs writer to write to destination file
    public JsonDatabaseWriter(String destination) {
        super(destination);
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of UserDatabase to file
    public void write(UserDatabase userDatabase) {
        JSONObject json = userDatabase.toJson();
        this.saveToFile(json.toString(4));
    }
}
