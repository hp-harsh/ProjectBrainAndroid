package hp.harsh.projectbrain.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AppCompatActivity;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import javax.inject.Inject;

import hp.harsh.projectbrain.R;


public class CommonUtil {
    private static String TAG = CommonUtil.class.getSimpleName();

    public final static Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9]+[_A-Za-z0-9-]*(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+\\.[A-Za-z0-9]+(\\.([A-Za-z]{2,}))?$");
    public final static Pattern INVALID_EMAIL_PATTERN = Pattern.compile("^[0-9-]+[_0-9-]*(\\.[_0-9-]+)*@[A-Za-z0-9]+\\.[A-Za-z0-9]+(\\.([A-Za-z]{2,}))?$");

    private ResourceUtil mResourceUtil;

    @Inject
    public CommonUtil(ResourceUtil resourceUtil) {
        this.mResourceUtil = resourceUtil;
    }

    public boolean checkEmail(String email) {
        if (EMAIL_PATTERN.matcher(email).matches() && !INVALID_EMAIL_PATTERN.matcher(email).matches()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isNullString(String string) {
        try {
            if (string.equalsIgnoreCase("null") || string == null || string.length() < 0 || string.equals("")) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public boolean isInternetAvailable(Context context) {
        ConnectionDetector cd = new ConnectionDetector(context);
        Boolean isInternetPresent = cd.isConnectingToInternet();

        if (!isInternetPresent) {
            ToastUtil.show(context, mResourceUtil.getString(R.string.toast_no_internet));
            return false;
        } else {
            return isInternetPresent;
        }
    }

    public void showSoftKeyboard(Context context) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.toggleSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(), InputMethodManager.SHOW_FORCED, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hideSoftKeyboard(Context context) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);

            View focusedView = ((AppCompatActivity) context).getCurrentFocus();

            //If no view is focused, an NPE will be thrown
            if (focusedView != null) {
                inputMethodManager.hideSoftInputFromWindow(((AppCompatActivity) context).getCurrentFocus().getWindowToken(), 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
