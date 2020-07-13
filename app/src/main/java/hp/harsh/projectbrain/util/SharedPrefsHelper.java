package hp.harsh.projectbrain.util;

import android.content.SharedPreferences;

import javax.inject.Inject;

import hp.harsh.projectbrain.annotations.ApiServiceScope;

@ApiServiceScope
public class SharedPrefsHelper {

    private static String TAG = "SharedPrefsHelper";

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Inject
    public SharedPrefsHelper(SharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;
        mEditor = mSharedPreferences.edit();
    }

    public void saveLoginData(String username, String firstname, String lastname, String email) {
        set("username", "" + username);
        set("firstname", "" + firstname);
        set("lastname", "" + lastname);
        set("email", "" + email);
    }

    public String getUsername() {
        return "" + mSharedPreferences.getString("username","");
    }

    public String get(String key) {
        return "" + mSharedPreferences.getString("" + key,"");
    }

    public void set(String key, String value) {
        mEditor.putString("" + key, "" + value);
        mEditor.commit();
    }

    public void clear() {
        mEditor.clear();
        mEditor.commit();
    }
}
