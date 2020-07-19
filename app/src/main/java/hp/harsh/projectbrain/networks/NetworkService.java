package hp.harsh.projectbrain.networks;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import java.util.Map;

import javax.inject.Inject;

import hp.harsh.projectbrain.models.BrainIdeaModel;
import hp.harsh.projectbrain.models.BrainTodoModel;
import hp.harsh.projectbrain.models.FollowModel;
import hp.harsh.projectbrain.models.IdeaRemovedModel;
import hp.harsh.projectbrain.models.LoginModel;
import hp.harsh.projectbrain.models.NewIdeaModel;
import hp.harsh.projectbrain.models.RegistrationModel;
import hp.harsh.projectbrain.models.TodoModel;
import hp.harsh.projectbrain.models.UpdateProfileModel;
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

    public void doUserLogin(Context context, Map<String, String> options,
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

    public void doUserRegister(Context context, Map<String, String> options,
                             boolean isSilentProgress, NetworkCallback callback) {

        if (!isSilentProgress) {
            initProgressDialog(context);
        }

        apiService.getUserRegistration(options)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends RegistrationModel>>() {
                    @Override
                    public ObservableSource<? extends RegistrationModel> apply(Throwable throwable) throws Exception {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(getObserver(callback, isSilentProgress));
    }

    public void doUpdateProfile(Context context, Map<String, String> options,
                               boolean isSilentProgress, NetworkCallback callback) {

        if (!isSilentProgress) {
            initProgressDialog(context);
        }

        apiService.getUserUpdateProfile(options)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends UpdateProfileModel>>() {
                    @Override
                    public ObservableSource<? extends UpdateProfileModel> apply(Throwable throwable) throws Exception {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(getObserver(callback, isSilentProgress));
    }

    public void doAddIdeaProfile(Context context, Map<String, String> options,
                                boolean isSilentProgress, NetworkCallback callback) {

        if (!isSilentProgress) {
            initProgressDialog(context);
        }

        apiService.getNewIdea(options)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends NewIdeaModel>>() {
                    @Override
                    public ObservableSource<? extends NewIdeaModel> apply(Throwable throwable) throws Exception {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(getObserver(callback, isSilentProgress));
    }

    public void doBrainIdea(Context context, String username,
                                 boolean isSilentProgress, NetworkCallback callback) {

        if (!isSilentProgress) {
            initProgressDialog(context);
        }

        apiService.getBrainIdea(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends BrainIdeaModel>>() {
                    @Override
                    public ObservableSource<? extends BrainIdeaModel> apply(Throwable throwable) throws Exception {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(getObserver(callback, isSilentProgress));
    }

    public void doBrainTodo(Context context, String username,
                            boolean isSilentProgress, NetworkCallback callback) {

        if (!isSilentProgress) {
            initProgressDialog(context);
        }

        apiService.getBrainTodo(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends BrainTodoModel>>() {
                    @Override
                    public ObservableSource<? extends BrainTodoModel> apply(Throwable throwable) throws Exception {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(getObserver(callback, isSilentProgress));
    }

    public void doIdea(Context context, boolean isSilentProgress, NetworkCallback callback) {

        if (!isSilentProgress) {
            initProgressDialog(context);
        }

        apiService.getIdea()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends BrainIdeaModel>>() {
                    @Override
                    public ObservableSource<? extends BrainIdeaModel> apply(Throwable throwable) throws Exception {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(getObserver(callback, isSilentProgress));
    }

    public void doFollow(Context context, Map<String, String> options, boolean isSilentProgress, NetworkCallback callback) {

        if (!isSilentProgress) {
            initProgressDialog(context);
        }

        apiService.getFollow(options)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends FollowModel>>() {
                    @Override
                    public ObservableSource<? extends FollowModel> apply(Throwable throwable) throws Exception {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(getObserver(callback, isSilentProgress));
    }

    public void doTodo(Context context, Map<String, String> options, boolean isSilentProgress, NetworkCallback callback) {

        if (!isSilentProgress) {
            initProgressDialog(context);
        }

        apiService.getTodo(options)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends TodoModel>>() {
                    @Override
                    public ObservableSource<? extends TodoModel> apply(Throwable throwable) throws Exception {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(getObserver(callback, isSilentProgress));
    }

    public void doRemove(Context context, String id, boolean isSilentProgress, NetworkCallback callback) {

        if (!isSilentProgress) {
            initProgressDialog(context);
        }

        apiService.getIdeaRemove("" + id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends IdeaRemovedModel>>() {
                    @Override
                    public ObservableSource<? extends IdeaRemovedModel> apply(Throwable throwable) throws Exception {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(getObserver(callback, isSilentProgress));
    }

    public void doGetIdea(Context context, String id, boolean isSilentProgress, NetworkCallback callback) {

        if (!isSilentProgress) {
            initProgressDialog(context);
        }

        apiService.getIdea("" + id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends NewIdeaModel>>() {
                    @Override
                    public ObservableSource<? extends NewIdeaModel> apply(Throwable throwable) throws Exception {
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
