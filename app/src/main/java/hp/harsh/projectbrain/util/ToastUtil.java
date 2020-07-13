package hp.harsh.projectbrain.util;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.Toast;

import com.github.johnpersano.supertoasts.SuperToast;

import hp.harsh.projectbrain.R;

public class ToastUtil {

    public static final boolean isToast = true;
    public static final String SF_TEXT_BOLD_OTF = "SFText_Bold.otf";
    private static Typeface mTypeFace;

    public static void show(Context context, String message) {

        if (isToast) {
            Toast.makeText(context, "" + message, Toast.LENGTH_SHORT).show();
        }
    }

    public static void show(Context context, String message, int timeInMilliSecond) {

        if (timeInMilliSecond <= 0) {
            return;
        }

        if (isToast) {
            try {
                mTypeFace = Typeface.createFromAsset(context.getAssets(), SF_TEXT_BOLD_OTF);
                final SuperToast superToast = new SuperToast(context);
                if (!superToast.isShowing()) {
                    superToast.setText(message);
                    //superToast.setIcon(R.mipmap.ic_launcher, SuperToast.IconPosition.LEFT);
                    superToast.setTextSize(SuperToast.TextSize.MEDIUM);
                    //superToast.getTextView().setTypeface(mTypeFace);
                    superToast.setTextColor(context.getResources().getColor(R.color.black));
                    superToast.setBackground(SuperToast.Background.WHITE);
                    superToast.setDuration(timeInMilliSecond);
                    superToast.setAnimations(SuperToast.Animations.SCALE);
                    superToast.show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
