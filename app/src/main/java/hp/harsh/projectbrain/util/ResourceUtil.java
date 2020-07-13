package hp.harsh.projectbrain.util;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import androidx.annotation.ArrayRes;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;

import hp.harsh.projectbrain.MyApplication;

public class ResourceUtil {

    public ResourceUtil() {
    }

    public String getString(@StringRes int stringId) {
        return MyApplication.getInstance().getString(stringId);
    }

    public String[] getStringArray(@ArrayRes int stringId) {
        return MyApplication.getInstance().getResources().getStringArray(stringId);
    }

    public Drawable getDrawable(@DrawableRes int drawableId) {
        return ContextCompat.getDrawable(MyApplication.getInstance(), drawableId);
    }

    public int getColor(@ColorRes int colorId) {
        return MyApplication.getInstance().getResources().getColor(colorId);
    }

    public AssetManager getAsset() {
        return MyApplication.getInstance().getAssets();
    }

    public int getDimen(@DimenRes int dimenId) {
        return (int) MyApplication.getInstance().getResources().getDimension(dimenId);
    }

    public int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

}
