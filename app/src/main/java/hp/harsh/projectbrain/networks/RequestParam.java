package hp.harsh.projectbrain.networks;

import java.util.HashMap;
import java.util.Map;

public class RequestParam {

    private static String TAG = "RequestParam";

    public static Map<String, String> getNull() {
        Map<String, String> mParam = new HashMap<String, String>();
        return mParam;
    }

    public static Map<String, String> paramCommon() {
        Map<String, String> requestBody = new HashMap<>();
        return requestBody;
    }

    public static Map<String, String> paramUserLogin(String email, String password) {

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("email", email.trim());
        requestBody.put("password", password.trim());
        return requestBody;
    }

    public static Map<String, String> paramUserRegister(String username, String email, String password,
                                                        String firstname, String lastname) {

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("username", username.trim());
        requestBody.put("email", email.trim());
        requestBody.put("password", password.trim());
        requestBody.put("firstname", firstname.trim());
        requestBody.put("lastname", lastname.trim());
        return requestBody;
    }

    public static Map<String, String> paramUserRegister(String username, String firstname, String lastname) {

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("username", username.trim());
        requestBody.put("firstname", firstname.trim());
        requestBody.put("lastname", lastname.trim());
        return requestBody;
    }

    public static Map<String, String> paramAddIdea(String username, String title,
                                                   String context, String content) {

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("username", username.trim());
        requestBody.put("title", title.trim());
        requestBody.put("context", context.trim());
        requestBody.put("content", content.trim());
        return requestBody;
    }

    public static Map<String, String> paramCiteIdea(String originalId, String username, String title,
                                                   String context, String content) {

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("originalId", originalId.trim());
        requestBody.put("username", username.trim());
        requestBody.put("title", title.trim());
        requestBody.put("context", context.trim());
        requestBody.put("content", content.trim());
        return requestBody;
    }

    public static Map<String, String> paramFollow(String username, String usernameToBeFollowed) {

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("username", username.trim());
        requestBody.put("usernameToBeFollowed", usernameToBeFollowed.trim());
        return requestBody;
    }

    public static Map<String, String> paramTodo(String username, String ideaId) {

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("username", username.trim());
        requestBody.put("ideaId", ideaId.trim());
        return requestBody;
    }
}