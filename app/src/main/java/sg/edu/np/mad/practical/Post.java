package sg.edu.np.mad.practical;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Post {
    public String username;
    public String password;

    public Post() {

    }

    public Post(String password, String username) {
        this.password = password;
        this.username = username;
    }

    /*
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("password", password);
        result.put("username", username);

        return result;
    }*/

}
