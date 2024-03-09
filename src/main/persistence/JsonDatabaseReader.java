package persistence;

import model.User;
import model.UserDatabase;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

// Represents a reader that reads UserDatabase from JSON data stored in file
public class JsonDatabaseReader extends JsonReader {

    // EFFECTS: constructs reader to read from source file
    public JsonDatabaseReader(String source) {
        super(source);
    }

    // EFFECTS: reads UserDatabase from file and returns it;
    // throws IOException if an error occurs reading data from file
    public UserDatabase readDatabase() throws IOException {
        String jsonData = this.readFile(this.source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return this.parseDatabase(jsonObject);
    }

    // EFFECTS: parses UserDatabase from JSON object and returns it
    protected UserDatabase parseDatabase(JSONObject jsonObject) {
        UserDatabase userDatabase = new UserDatabase();
        addUsers(userDatabase, jsonObject);
        return userDatabase;
    }

    // MODIFIES: userDatabase
    // EFFECTS: parses userDatabase from JSON object and adds them to userDatabase
    protected void addUsers(UserDatabase userDatabase, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("userDatabase");
        for (Object json : jsonArray) {
            JSONObject nextUser = (JSONObject) json;
            addUser(userDatabase, nextUser);
        }
    }

    // MODIFIES: userDatabase
    // EFFECTS: parses user from JSON object and adds them to userDatabase
    protected void addUser(UserDatabase userDatabase, JSONObject jsonObject) {
        User user = super.parseUser(jsonObject);
        userDatabase.addUser(user);
    }


}
