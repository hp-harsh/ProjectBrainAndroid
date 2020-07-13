package hp.harsh.projectbrain.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import javax.inject.Inject;

import hp.harsh.projectbrain.MyApplication;
import hp.harsh.projectbrain.R;
import hp.harsh.projectbrain.components.DaggerLoginActivityComponent;
import hp.harsh.projectbrain.components.LoginActivityComponent;
import hp.harsh.projectbrain.models.LoginModel;
import hp.harsh.projectbrain.modules.LoginActivityModule;
import hp.harsh.projectbrain.networks.NetworkCallback;
import hp.harsh.projectbrain.networks.NetworkError;
import hp.harsh.projectbrain.networks.NetworkService;
import hp.harsh.projectbrain.networks.RequestParam;
import hp.harsh.projectbrain.util.CommonUtil;
import hp.harsh.projectbrain.util.ResourceUtil;
import hp.harsh.projectbrain.util.RxUtil;
import hp.harsh.projectbrain.util.SharedPrefsHelper;
import hp.harsh.projectbrain.util.ToastUtil;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, NetworkCallback {

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

    private TextView mTxtTitle;

    private EditText mEdtEmail;
    private EditText mEdtPassword;

    private Button mBtnSubmit;
    private Button mBtnRegistration;

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

        init();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSubmit:
                checkValidation();
                break;

            case R.id.btnRegistration:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        // Quit app if user double tap back pressed
        mRxUtil.quitApp(LoginActivity.this);
    }

    private void init() {
        mTxtTitle = findViewById(R.id.txtTitle);

        mEdtEmail = findViewById(R.id.edtEmail);
        mEdtPassword = findViewById(R.id.edtPassword);

        mBtnSubmit = findViewById(R.id.btnSubmit);
        mBtnRegistration = findViewById(R.id.btnRegistration);

        mBtnSubmit.setOnClickListener(this);
        mBtnRegistration.setOnClickListener(this);

        mTxtTitle.setText(mResourceUtil.getString(R.string.login));
    }

    private void checkValidation() {
        if (mCommonUtil.isNullString("" + mEdtEmail.getText().toString().trim())) {
            ToastUtil.show(LoginActivity.this, mResourceUtil.getString(R.string.toast_email_empty));

        } else if (mCommonUtil.isNullString("" + mEdtPassword.getText().toString().trim())) {
            ToastUtil.show(LoginActivity.this, mResourceUtil.getString(R.string.toast_password_empty));

        } else {
            callLoginApi();
        }
    }

    private void callLoginApi() {
        Log.i(TAG, "callLoginApi");
        if (!mCommonUtil.isInternetAvailable(LoginActivity.this)) {
            return;
        }

        // Now call web service using retrofit
        mNetworkService.doUserSignIn(LoginActivity.this,
                RequestParam.paramUserLogin("" + mEdtEmail.getText().toString(), "" + mEdtPassword.getText().toString()),
                false,
                this);
    }

    @Override
    public void onSuccess(Object networkResponse) {
        if (networkResponse instanceof LoginModel) {
            LoginModel responseModel = (LoginModel) networkResponse;

            if (responseModel != null) {
                Toast.makeText(
                        LoginActivity.this,
                        R.string.success,
                        Toast.LENGTH_LONG
                ).show();

                mSharedPrefsHelper.saveLoginData(responseModel.getUsername(),
                        responseModel.getFirstname(), responseModel.getLastname(), responseModel.getEmail());

                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                finish();
            }
        } else {
            ToastUtil.show(LoginActivity.this, mResourceUtil.getString(R.string.toast_something_wrong));
        }
    }

    @Override
    public void onError(NetworkError networkError) {
        ToastUtil.show(LoginActivity.this, "" + networkError.getMessage());
    }
}
