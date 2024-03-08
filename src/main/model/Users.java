package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// Represents app users list that helps to manage register and login
public class Users {
    private ArrayList<User> users;

    //EFFECTS: create a new users list
    public Users() {
        this.users = new ArrayList<>();
    }


//    Second layer data persistence
//    Do it later
//    // EFFECTS: returns all Users in JSON
//    @Override
//    public JSONObject toJson() {
//        JSONObject json = new JSONObject();
//        json.put("usersList", usersToJson());
//        return json;
//    }
//
//    // EFFECTS: returns all users in this Users as a JSON array
//    private JSONArray usersToJson() {
//        JSONArray jsonArray = new JSONArray();
//        for (User user : users) {
//            jsonArray.put(user.getUserName());
//        }
//        return jsonArray;
//    }

    //REQUIRES: name has a non-zero length
    //EFFECTS: search the given name in users list, if exists return this user.
    //         If not in the list, return null.
    public User findUser(String name) {
        for (User user : users) {
            if (user.getUserName().equals(name)) {
                return user;
            }
        }
        return null;
    }

    //REQUIRES: name has a non-zero length
    //EFFECTS: search the given name in users list, if exists return false,
    //         If not in the list, create and add this new user to the list
    public boolean registerUser(String userName) {
        if (findUser(userName) == null) {
            users.add(new User(userName));
            return true;
        } else {
            return false;
        }
    }

    // MODIFIES: this
    // EFFECTS: remove the given user from users list
    //         return true for success, false otherwise
    public boolean removeUser(User user) {
        if (this.users.contains(user)) {
            this.users.remove(user);
            return true;
        }
        return false;
    }

    // !!! Put all simple setter and getter methods below
    public ArrayList<User> getUsers() {
        return this.users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }


}
