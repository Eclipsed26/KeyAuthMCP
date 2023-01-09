package Client.Auth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserData {
    private final String username;
    private final String subscription;
    private final String expiry;

    public UserData(JSONObject json) throws JSONException {
        JSONObject info = json.getJSONObject("info");

        JSONArray subArray = info.getJSONArray("subscriptions");
        JSONObject subObject = subArray.getJSONObject(0);

        this.username = info.getString("username");
        this.subscription = subObject.getString("subscription");
        this.expiry = subObject.getString("expiry");
    }
}
