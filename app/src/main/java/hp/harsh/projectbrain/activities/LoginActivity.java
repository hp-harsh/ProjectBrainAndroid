package hp.harsh.projectbrain.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import javax.inject.Inject;

import hp.harsh.projectbrain.MyApplication;
import hp.harsh.projectbrain.R;
import hp.harsh.projectbrain.components.DaggerLoginActivityComponent;
import hp.harsh.projectbrain.components.LoginActivityComponent;
import hp.harsh.projectbrain.modules.LoginActivityModule;
import hp.harsh.projectbrain.networks.NetworkCallback;
import hp.harsh.projectbrain.networks.NetworkService;
import hp.harsh.projectbrain.util.CommonUtil;
import hp.harsh.projectbrain.util.ResourceUtil;
import hp.harsh.projectbrain.util.RxUtil;
import hp.harsh.projectbrain.util.SharedPrefsHelper;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    private LoginActivityComponent mComponent;

    @Inject
    NetworkService mNetworkService;

    @Inject
    SharedPrefsHelper mSharedPrefsHelper;

    @Inject
    RxUtil mRxUtil;

    @Inject
    ResourceUtil mResourceUtil;

    @Inject
    CommonUtil mCommonUtil;

    private Button mBtnSubmit;
    private Button mBtnFacebook;

    private TextView mTxtForgotPassword;
    private TextView mTxtNoAccount;
    private TextView mTxtRegister;

    private EditText mEdtEmail;
    private EditText mEdtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mComponent = DaggerLoginActivityComponent.builder()
                .loginActivityModule(new LoginActivityModule(LoginActivity.this))
                .myApplicationComponent(MyApplication.getInstance().getApplicationComponent())
                .build();

        mComponent.loginActivityInject(LoginActivity.this);

        // Hide keyboard if visible
        mCommonUtil.hideSoftKeyboard(this);
    }

    @Override
    public void onBackPressed() {
        // Quit app if user double tap back pressed
        mRxUtil.quitApp(LoginActivity.this);
    }
}
