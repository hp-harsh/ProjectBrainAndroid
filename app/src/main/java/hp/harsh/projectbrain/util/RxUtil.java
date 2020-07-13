package hp.harsh.projectbrain.util;

import android.app.Activity;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import hp.harsh.projectbrain.R;
import hp.harsh.projectbrain.interfaces.SplashInterface;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RxUtil {

    public static String TAG = RxUtil.class.getSimpleName();

    private static boolean mDoubleBackToExitPressedOnce = false;

    private ResourceUtil mResourceUtil;

    @Inject
    public RxUtil(ResourceUtil resourceUtil) {
        this.mResourceUtil = resourceUtil;
    }

    public void waitForSplashTimeOut(final SplashInterface mSplashInterface) {
        Single.just(new Object())
                .delay(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Object>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Object o) {
                        mSplashInterface.onSplashTimeOver();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public void quitApp(Activity activity) {
        if (activity == null) {
            return;
        }

        if (mDoubleBackToExitPressedOnce) {
            activity.finish();
        }

        if (!mDoubleBackToExitPressedOnce) {
            mDoubleBackToExitPressedOnce = true;
            ToastUtil.show(activity, mResourceUtil.getString(R.string.toast_press_to_close));
        }

        checkDoubleBackPressTime();
    }

    private void checkDoubleBackPressTime() {
        Single.just(new Object())
                .delay(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }

    private SingleObserver<Object> getObserver() {
        return new SingleObserver<Object>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Object o) {
                mDoubleBackToExitPressedOnce = false;
            }

            @Override
            public void onError(Throwable e) {

            }
        };
    }
}
