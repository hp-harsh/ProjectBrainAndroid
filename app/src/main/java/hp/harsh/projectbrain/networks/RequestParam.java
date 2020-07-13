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
}