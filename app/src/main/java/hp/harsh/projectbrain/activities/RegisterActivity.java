package hp.harsh.projectbrain.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import javax.inject.Inject;

import hp.harsh.projectbrain.MyApplication;
import hp.harsh.projectbrain.R;
import hp.harsh.projectbrain.components.DaggerRegisterActivityComponent;
import hp.harsh.projectbrain.components.RegisterActivityComponent;
import hp.harsh.projectbrain.models.LoginModel;
import hp.harsh.projectbrain.models.RegistrationModel;
import hp.harsh.projectbrain.modules.RegisterActivityModule;
import hp.harsh.projectbrain.networks.NetworkCallback;
import hp.harsh.projectbrain.networks.NetworkError;
import hp.harsh.projectbrain.networks.NetworkService;
import hp.harsh.projectbrain.networks.RequestParam;
import hp.harsh.projectbrain.util.CommonUtil;
import hp.harsh.projectbrain.util.ResourceUtil;
import hp.harsh.projectbrain.util.RxUtil;
import hp.harsh.projectbrain.util.SharedPrefsHelper;
import hp.harsh.projectbrain.util.ToastUtil;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, NetworkCallback {

    private static final String TAG = RegisterActivity.class.getSimpleName();

    private RegisterActivityComponent mComponent;

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

    private EditText edtUsername;
    private EditText edtEmail;
    private EditText edtFirstname;
    private EditText edtLastname;
    private EditText edtPassword;

    private Button btnSubmit;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mComponent = DaggerRegisterActivityComponent.builder()
                .registerActivityModule(new RegisterActivityModule(RegisterActivity.this))
                .myApplicationComponent(MyApplication.getInstance().getApplicationComponent())
                .build();

        mComponent.registerActivityInject(RegisterActivity.this);

        // Hide keyboard if visible
        mCommonUtil.hideSoftKeyboard(this);

        init();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSubmit:
                checkValidation();
                break;

            case R.id.btnLogin:
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
                break;
        }
    }

    private void init() {
        mTxtTitle = findViewById(R.id.txtTitle);

        edtUsername = findViewById(R.id.edtUsername);
        edtEmail = findViewById(R.id.edtEmail);
        edtFirstname = findViewById(R.id.edtFirstname);
        edtLastname = findViewById(R.id.edtLastname);
        edtPassword = findViewById(R.id.edtPassword);

        btnSubmit = findViewById(R.id.btnSubmit);
        btnLogin = findViewById(R.id.btnLogin);

        btnSubmit.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        mTxtTitle.setText(mResourceUtil.getString(R.string.login));
    }

    private void checkValidation() {
        if (mCommonUtil.isNullString("" + edtUsername.getText().toString().trim())) {
            ToastUtil.show(RegisterActivity.this, mResourceUtil.getString(R.string.toast_username_empty));

        } else if (mCommonUtil.isNullString("" + edtEmail.getText().toString().trim())) {
            ToastUtil.show(RegisterActivity.this, mResourceUtil.getString(R.string.toast_email_empty));

        } else if (mCommonUtil.isNullString("" + edtFirstname.getText().toString().trim())) {
            ToastUtil.show(RegisterActivity.this, mResourceUtil.getString(R.string.toast_firstname_empty));

        } else if (mCommonUtil.isNullString("" + edtLastname.getText().toString().trim())) {
            ToastUtil.show(RegisterActivity.this, mResourceUtil.getString(R.string.toast_lastname_empty));

        } else if (mCommonUtil.isNullString("" + edtPassword.getText().toString().trim())) {
            ToastUtil.show(RegisterActivity.this, mResourceUtil.getString(R.string.toast_password_empty));

        } else {
            callRegistrationApi();
        }
    }

    private void callRegistrationApi() {
        Log.i(TAG, "callLoginApi");
        if (!mCommonUtil.isInternetAvailable(RegisterActivity.this)) {
            return;
        }

        // Now call web service using retrofit
        mNetworkService.doUserRegister(RegisterActivity.this,
                RequestParam.paramUserRegister("" + edtUsername.getText().toString(),
                        "" + edtEmail.getText().toString(),
                        "" + edtPassword.getText().toString(),
                        "" + edtFirstname.getText().toString(),
                        "" + edtLastname.getText().toString()),
                false,
                this);
    }

    @Override
    public void onSuccess(Object networkResponse) {
        if (networkResponse instanceof RegistrationModel) {

            RegistrationModel responseModel = (RegistrationModel) networkResponse;

            if (responseModel != null) {
                Toast.makeText(
                        RegisterActivity.this,
                        R.string.success,
                        Toast.LENGTH_LONG
                ).show();

                mSharedPrefsHelper.saveLoginData(responseModel.getUsername(),
                        responseModel.getFirstname(), responseModel.getLastname(), responseModel.getEmail());

                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }

        } else {
            ToastUtil.show(RegisterActivity.this, mResourceUtil.getString(R.string.toast_something_wrong));
        }
    }

    @Override
    public void onError(NetworkError networkError) {
        ToastUtil.show(RegisterActivity.this, "" + networkError.getMessage());
    }

}
