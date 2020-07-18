package hp.harsh.projectbrain.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import hp.harsh.projectbrain.R;
import hp.harsh.projectbrain.models.BrainIdeaModel;
import hp.harsh.projectbrain.networks.NetworkCallback;
import hp.harsh.projectbrain.networks.NetworkError;
import hp.harsh.projectbrain.util.ToastUtil;

public class SearchFragment extends BaseFragment implements NetworkCallback, View.OnClickListener, TextWatcher {
    private static final String TAG = SearchFragment.class.getSimpleName();

    private TextView mTxtTitle;
    private TextView txtNoData;
    private TextView txtIdeaTitle;
    private TextView txtContext;
    private TextView txtContent;
    private TextView txtPostedBy;

    private LinearLayout lnrData;

    private EditText edtSearch;
    private ArrayList<BrainIdeaModel.Datum> arrayBrainIdeas = new ArrayList<>();

    private ImageView imgSearch;
    private ImageView imgCite;
    private ImageView imgTodo;
    private ImageView imgFollow;

    private boolean isFirstTime = true;

    public static SearchFragment getInstance() {
        SearchFragment frag = new SearchFragment();
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);
    }

    private void init(View view) {
        mTxtTitle = view.findViewById(R.id.txtTitle);
        txtNoData = view.findViewById(R.id.txtNoData);
        txtIdeaTitle = view.findViewById(R.id.txtIdeaTitle);
        txtContext = view.findViewById(R.id.txtContext);
        txtContent = view.findViewById(R.id.txtContent);
        txtPostedBy = view.findViewById(R.id.txtPostedBy);

        edtSearch = view.findViewById(R.id.edtSearch);

        imgSearch = view.findViewById(R.id.imgSearch);
        imgCite = view.findViewById(R.id.imgCite);
        imgTodo = view.findViewById(R.id.imgTodo);
        imgFollow = view.findViewById(R.id.imgFollow);

        mTxtTitle.setText(mResourceUtil.getString(R.string.home));

        edtSearch.addTextChangedListener(this);
        imgSearch.setOnClickListener(this);
    }

    private void callBrainIdeaByUsernameApi(String username) {
        Log.i(TAG, "callLoginApi");
        if (!mCommonUtil.isInternetAvailable(getActivity())) {
            return;
        }

        if (mCommonUtil.isNullString("" + username.trim())) {
            ToastUtil.show(getActivity(), "" + mResourceUtil.getString(R.string.toast_username_empty));
            return;
        } else if (username.equals("" + mSharedPrefsHelper.getUsername().trim())) {
            ToastUtil.show(getActivity(), getString(R.string.other_username_empty));
            return;
        }

        // Now call web service using retrofit
        mNetworkService.doBrainIdea(getActivity(),
                username,
                false,
                this);
    }

    @Override
    public void onSuccess(Object networkResponse) {
        if (networkResponse instanceof BrainIdeaModel) {

            BrainIdeaModel responseModel = (BrainIdeaModel) networkResponse;

            if (arrayBrainIdeas.size() > 0) {
                arrayBrainIdeas = new ArrayList<>();
            }

            arrayBrainIdeas.addAll(responseModel.getData());

            if (arrayBrainIdeas.size() == 0) {
                txtNoData.setVisibility(View.VISIBLE);
                lnrData.setVisibility(View.GONE);
            } else {
                txtNoData.setVisibility(View.GONE);
                lnrData.setVisibility(View.VISIBLE);
            }

        } else {
            ToastUtil.show(getActivity(), mResourceUtil.getString(R.string.toast_something_wrong));
        }
    }

    @Override
    public void onError(NetworkError networkError) {
        ToastUtil.show(getActivity(), "" + networkError.getMessage());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgSearch:
                callBrainIdeaByUsernameApi("" + edtSearch.getText().toString().trim());
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (!isFirstTime && s.toString().trim().length() == 0) {
            txtNoData.setVisibility(View.VISIBLE);
            lnrData.setVisibility(View.GONE);
        }

        isFirstTime = false;
    }
}
