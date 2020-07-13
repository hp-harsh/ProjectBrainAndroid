package hp.harsh.projectbrain.networks;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import java.util.Map;

import javax.inject.Inject;

import hp.harsh.projectbrain.models.LoginModel;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class NetworkService {

    private static String TAG = NetworkService.class.getSimpleName();
    private final ApiService apiService;

    private ProgressDialog progressDialog;

    @Inject
    public NetworkService(ApiService apiService) {
        this.apiService = apiService;
    }

    public void doUserSignIn(Context context, Map<String, String> options,
                             boolean isSilentProgress, NetworkCallback callback) {

        if (!isSilentProgress) {
            initProgressDialog(context);
        }

        apiService.getUserLogin(options)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends LoginModel>>() {
                    @Override
                    public ObservableSource<? extends LoginModel> apply(Throwable throwable) throws Exception {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(getObserver(callback, isSilentProgress));
    }

    private Observer<Object> getObserver(final NetworkCallback callback, final boolean isSilentProgress) {
        return new Observer<Object>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object moviesResponse) {
                if (callback != null) {
                    callback.onSuccess(moviesResponse);
                }
            }

            @Override
            public void onError(Throwable e) {
                if (callback != null) {
                    callback.onError(new NetworkError(e));
                }

                closeProgressDialog(isSilentProgress);
            }

            @Override
            public void onComplete() {
                closeProgressDialog(isSilentProgress);
            }
        };
    }

    private void initProgressDialog(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please wait..");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Log.i(TAG, "initProgressDialog");
    }

    private void closeProgressDialog(boolean isSilentProgress) {
        if (progressDialog != null && !isSilentProgress) {
            progressDialog.dismiss();
            Log.i(TAG, "closeProgressDialog");
        }
    }
}
