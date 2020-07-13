package hp.harsh.projectbrain.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import hp.harsh.projectbrain.R;
import hp.harsh.projectbrain.activities.LoginActivity;
import hp.harsh.projectbrain.activities.RegisterActivity;
import hp.harsh.projectbrain.models.RegistrationModel;
import hp.harsh.projectbrain.models.UpdateProfileModel;
import hp.harsh.projectbrain.networks.NetworkCallback;
import hp.harsh.projectbrain.networks.NetworkError;
import hp.harsh.projectbrain.networks.RequestParam;
import hp.harsh.projectbrain.util.ToastUtil;

public class BrainUpdateProfileFragment extends BaseFragment implements View.OnClickListener, NetworkCallback {
    private static final String TAG = BrainUpdateProfileFragment.class.getSimpleName();

    private TextView mTxtTitle;

    private EditText edtUsername;
    private EditText edtEmail;
    private EditText edtFirstname;
    private EditText edtLastname;

    private Button btnSubmit;

    public static BrainUpdateProfileFragment getInstance() {
        BrainUpdateProfileFragment frag = new BrainUpdateProfileFragment();
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_update_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);
    }

    private void init(View view) {
        mTxtTitle = view.findViewById(R.id.txtTitle);

        edtUsername = view.findViewById(R.id.edtUsername);
        edtEmail = view.findViewById(R.id.edtEmail);
        edtFirstname = view.findViewById(R.id.edtFirstname);
        edtLastname = view.findViewById(R.id.edtLastname);

        btnSubmit = view.findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(this);

        mTxtTitle.setText(mResourceUtil.getString(R.string.update_profile));
        edtUsername.setText("" + mSharedPrefsHelper.getUsername());
        edtEmail.setText("" + mSharedPrefsHelper.get("email"));
        edtFirstname.setText("" + mSharedPrefsHelper.get("firstname"));
        edtLastname.setText("" + mSharedPrefsHelper.get("lastname"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSubmit:
                checkValidation();
                break;
        }
    }

    private void checkValidation() {
        if (mCommonUtil.isNullString("" + edtUsername.getText().toString().trim())) {
            ToastUtil.show(getActivity(), mResourceUtil.getString(R.string.toast_username_empty));

        } else if (mCommonUtil.isNullString("" + edtEmail.getText().toString().trim())) {
            ToastUtil.show(getActivity(), mResourceUtil.getString(R.string.toast_email_empty));

        } else if (mCommonUtil.isNullString("" + edtFirstname.getText().toString().trim())) {
            ToastUtil.show(getActivity(), mResourceUtil.getString(R.string.toast_firstname_empty));

        } else if (mCommonUtil.isNullString("" + edtLastname.getText().toString().trim())) {
            ToastUtil.show(getActivity(), mResourceUtil.getString(R.string.toast_lastname_empty));

        } else {
            callUpdateProfileApi();
        }
    }

    private void callUpdateProfileApi() {
        Log.i(TAG, "callLoginApi");
        if (!mCommonUtil.isInternetAvailable(getActivity())) {
            return;
        }

        // Now call web service using retrofit
        mNetworkService.doUpdateProfile(getActivity(),
                RequestParam.paramUserRegister("" + edtUsername.getText().toString(),
                        "" + edtFirstname.getText().toString(),
                        "" + edtLastname.getText().toString()),
                false,
                this);
    }

    @Override
    public void onSuccess(Object networkResponse) {
        if (networkResponse instanceof UpdateProfileModel) {

            UpdateProfileModel responseModel = (UpdateProfileModel) networkResponse;

            if (responseModel != null) {
                Toast.makeText(
                        getActivity(),
                        R.string.success,
                        Toast.LENGTH_LONG
                ).show();

                mSharedPrefsHelper.saveLoginData(responseModel.getUsername(),
                        responseModel.getFirstname(), responseModel.getLastname(), responseModel.getEmail());

                mActivityContext.onBackPressed();
            }

        } else {
            ToastUtil.show(getActivity(), mResourceUtil.getString(R.string.toast_something_wrong));
        }
    }

    @Override
    public void onError(NetworkError networkError) {
        ToastUtil.show(getActivity(), "" + networkError.getMessage());
    }
}
