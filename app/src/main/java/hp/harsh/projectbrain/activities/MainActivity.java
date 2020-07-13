package hp.harsh.projectbrain.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import javax.inject.Inject;

import hp.harsh.projectbrain.MyApplication;
import hp.harsh.projectbrain.R;
import hp.harsh.projectbrain.components.DaggerMainActivityComponent;
import hp.harsh.projectbrain.components.MainActivityComponent;
import hp.harsh.projectbrain.interfaces.SplashInterface;
import hp.harsh.projectbrain.modules.MainActivityModule;
import hp.harsh.projectbrain.util.CommonUtil;
import hp.harsh.projectbrain.util.RxUtil;
import hp.harsh.projectbrain.util.SharedPrefsHelper;

public class MainActivity extends AppCompatActivity implements SplashInterface {

    private static final String TAG = MainActivity.class.getSimpleName();

    private MainActivityComponent mComponent;

    @Inject
    RxUtil mRxUtil;

    @Inject
    SharedPrefsHelper mSharedPreferenceHelper;

    @Inject
    CommonUtil mCommonUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mComponent = DaggerMainActivityComponent.builder()
                .mainActivityModule(new MainActivityModule(MainActivity.this))
                .myApplicationComponent(MyApplication.getInstance().getApplicationComponent())
                .build();

        mComponent.mainActivityInject(MainActivity.this);

        mRxUtil.waitForSplashTimeOut(this);
    }


    @Override
    public void onSplashTimeOver() {
        // If user is already logged in, redirect to dashboard
        if (!(mCommonUtil.isNullString(mSharedPreferenceHelper.getUsername()))) {
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
            finish();
        } else {
            // If user is not logged in, redirect to login page
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }
    }

    @Override
    public void onSplashTimeError() {
        finish();
    }
}