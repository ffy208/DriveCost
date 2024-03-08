package persistence;

import org.json.JSONObject;

// an interface to force all subclass have Writeable function
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
