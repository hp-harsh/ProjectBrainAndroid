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
