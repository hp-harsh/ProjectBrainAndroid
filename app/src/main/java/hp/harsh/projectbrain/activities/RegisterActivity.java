package hp.harsh.projectbrain.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import javax.inject.Inject;

import hp.harsh.projectbrain.MyApplication;
import hp.harsh.projectbrain.R;
import hp.harsh.projectbrain.components.DaggerRegisterActivityComponent;
import hp.harsh.projectbrain.components.RegisterActivityComponent;
import hp.harsh.projectbrain.modules.RegisterActivityModule;
import hp.harsh.projectbrain.networks.NetworkService;
import hp.harsh.projectbrain.util.CommonUtil;
import hp.harsh.projectbrain.util.ResourceUtil;
import hp.harsh.projectbrain.util.RxUtil;
import hp.harsh.projectbrain.util.SharedPrefsHelper;

public class RegisterActivity extends AppCompatActivity {

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

    private Button mBtnSubmit;
    private ImageView mImgBack;

    private TextView mTxtTitle;

    private EditText mEdtEmail;
    private EditText mEdtFirstName;
    private EditText mEdtLastName;
    private EditText mEdtPhone;
    private EditText mEdtPassword;
    private EditText mEdtConfirmPassword;

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
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
