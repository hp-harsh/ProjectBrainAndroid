package hp.harsh.projectbrain.util;

import android.util.Log;

import org.json.JSONObject;

public class LogUtil {

    public static boolean isLog = true;

    public static void e(String tag, String message) {

        try {
            if (isLog)
                Log.e(tag, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void e(String tag, Boolean message) {
        try {
            if (isLog)
                Log.e(tag, "" + message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void e(String tag, JSONObject message) {
        try {
            if (isLog)
                Log.e(tag, "" + message.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void e(String tag, Integer message) {
        try {
            if (isLog)
                Log.e(tag, "" + message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void d(String tag, String message) {
        try {
            if (isLog)
                Log.d(tag, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void i(String tag, String message) {
        try {
            if (isLog)
                Log.i(tag, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void v(String tag, String message) {
        try {
            if (isLog)
                Log.v(tag, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}	