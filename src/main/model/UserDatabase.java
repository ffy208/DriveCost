package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents app users list that helps to manage register and login
public class UserDatabase implements Writable {
    private ArrayList<User> userDatabase;

    //EFFECTS: create a new users list
    public UserDatabase() {
        this.userDatabase = new ArrayList<>();
    }


    // EFFECTS: returns this UserDatabase in JSON
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("userDatabase", userDatabaseToJson());
        return json;
    }

    // EFFECTS: returns userDatabase as a JSON array
    private JSONArray userDatabaseToJson() {
        JSONArray jsonArray = new JSONArray();
        for (User user : userDatabase) {
            jsonArray.put(user.toJson());
        }
        return jsonArray;
    }

    //REQUIRES: name has a non-zero length
    //EFFECTS: search the given name in users list, if exists return this user.
    //         If not in the list, return null.
    public User findUser(String name) {
        for (User user : userDatabase) {
            if (user.getUserName().equals(name)) {
                return user;
            }
        }
        return null;
    }

    //REQUIRES: name has a non-zero length
    //EFFECTS: search the given name in users list, if exists return false,
    //         If not in the list, create and add this new user to the list, and log the event.
    public boolean registerUser(String userName) {
        if (findUser(userName) == null) {
            userDatabase.add(new User(userName));
            EventLog.getInstance().logEvent(new Event("New User " + userName + "has been added to Database"));
            return true;
        } else {
            return false;
        }
    }

    //REQUIRES: User not in the DataBase
    //MODIFIES: this
    //EFFECTS: Add User into Database, and log the event.
    public void addUser(User user) {
        this.userDatabase.add(user);
        EventLog.getInstance().logEvent(new Event("User "
                + user.getUserName() + " has been added to Database."));
    }

    // MODIFIES: this
    // EFFECTS: remove the given user from users list
    //         return true for success, false otherwise, and log the event for both .
    public boolean removeUser(User user) {
        if (this.userDatabase.contains(user)) {
            this.userDatabase.remove(user);
            EventLog.getInstance().logEvent(new Event("User: "
                    + user.getUserName() + " has been removed from Database"));
            return true;
        }
        EventLog.getInstance().logEvent(new Event("User: "
                + user.getUserName() + " remove failed from Database"));
        return false;
    }

    // !!! Put all simple setter and getter methods below
    public ArrayList<User> getUserDatabase() {
        return this.userDatabase;
    }

}
