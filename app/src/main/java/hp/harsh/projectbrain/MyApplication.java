package hp.harsh.projectbrain;

import android.app.Activity;
import android.app.Application;

import hp.harsh.projectbrain.components.DaggerMyApplicationComponent;
import hp.harsh.projectbrain.components.MyApplicationComponent;
import hp.harsh.projectbrain.modules.ContextModule;
import timber.log.Timber;

public class MyApplication extends Application {

    private static final String TAG = MyApplication.class.getSimpleName();

    public static MyApplication get(Activity activity) {
        return (MyApplication) activity.getApplication();
    }

    private static MyApplication mInstance;

    MyApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;

        Timber.plant(new Timber.DebugTree());

        component = DaggerMyApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public MyApplicationComponent getApplicationComponent() {
        return component;
    }
}
