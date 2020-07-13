package hp.harsh.projectbrain.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import hp.harsh.projectbrain.R;
import hp.harsh.projectbrain.models.NewIdeaModel;
import hp.harsh.projectbrain.models.RegistrationModel;
import hp.harsh.projectbrain.networks.NetworkCallback;
import hp.harsh.projectbrain.networks.NetworkError;
import hp.harsh.projectbrain.networks.RequestParam;
import hp.harsh.projectbrain.util.ToastUtil;

public class NewIdeaFragment extends BaseFragment implements View.OnClickListener, NetworkCallback {
    private static final String TAG = NewIdeaFragment.class.getSimpleName();

    private TextView mTxtTitle;

    private EditText edtTitle;
    private EditText edtContext;
    private EditText edtContent;

    private Button btnSubmit;

    public static NewIdeaFragment getInstance() {
        NewIdeaFragment frag = new NewIdeaFragment();
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_idea, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);
    }

    private void init(View view) {
        mTxtTitle = view.findViewById(R.id.txtTitle);

        edtTitle = view.findViewById(R.id.edtTitle);
        edtContext = view.findViewById(R.id.edtContext);
        edtContent = view.findViewById(R.id.edtContent);

        btnSubmit = view.findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(this);

        mTxtTitle.setText(mResourceUtil.getString(R.string.new_idea));
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
        if (mCommonUtil.isNullString("" + edtTitle.getText().toString().trim())) {
            ToastUtil.show(getActivity(), mResourceUtil.getString(R.string.toast_title_empty));

        } else if (mCommonUtil.isNullString("" + edtContext.getText().toString().trim())) {
            ToastUtil.show(getActivity(), mResourceUtil.getString(R.string.toast_context_empty));

        } else if (mCommonUtil.isNullString("" + edtContent.getText().toString().trim())) {
            ToastUtil.show(getActivity(), mResourceUtil.getString(R.string.toast_content_empty));

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
        mNetworkService.doAddIdeaProfile(getActivity(),
                RequestParam.paramAddIdea("" + mSharedPrefsHelper.getUsername(),
                        "" + edtTitle.getText().toString(),
                        "" + edtContext.getText().toString(),
                        "" + edtContent.getText().toString()),
                false,
                this);
    }

    @Override
    public void onSuccess(Object networkResponse) {
        if (networkResponse instanceof NewIdeaModel) {

            NewIdeaModel responseModel = (NewIdeaModel) networkResponse;

            if (responseModel != null) {
                Toast.makeText(
                        getActivity(),
                        R.string.success,
                        Toast.LENGTH_LONG
                ).show();

                edtTitle.setText("");
                edtContext.setText("");
                edtContent.setText("");
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
