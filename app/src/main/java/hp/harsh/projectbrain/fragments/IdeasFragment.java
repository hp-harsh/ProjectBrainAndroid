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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hp.harsh.projectbrain.R;
import hp.harsh.projectbrain.adapters.HomeIdeasAdapter;
import hp.harsh.projectbrain.adapters.IdeasAdapter;
import hp.harsh.projectbrain.events.OnIdeaRemoved;
import hp.harsh.projectbrain.models.BrainIdeaModel;
import hp.harsh.projectbrain.networks.NetworkCallback;
import hp.harsh.projectbrain.networks.NetworkError;
import hp.harsh.projectbrain.util.ToastUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class IdeasFragment extends BaseFragment implements NetworkCallback, View.OnClickListener, TextWatcher {
    private static final String TAG = IdeasFragment.class.getSimpleName();

    private TextView mTxtTitle;
    private TextView txtNoData;

    private RecyclerView recyclerView;

    private EditText edtSearch;

    private HomeIdeasAdapter ideasAdapter;
    private ArrayList<BrainIdeaModel.Datum> arrayBrainIdeas = new ArrayList<>();

    private ImageView imgSearch;

    private boolean isFirstTime = true;

    public static IdeasFragment getInstance() {
        IdeasFragment frag = new IdeasFragment();
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);
    }

    private void init(View view) {
        mTxtTitle = view.findViewById(R.id.txtTitle);
        txtNoData = view.findViewById(R.id.txtNoData);

        edtSearch = view.findViewById(R.id.edtSearch);

        imgSearch = view.findViewById(R.id.imgSearch);

        recyclerView = view.findViewById(R.id.recyclerView);

        mTxtTitle.setText(mResourceUtil.getString(R.string.home));


        initList();

        callBrainIdeaApi();

        edtSearch.addTextChangedListener(this);
        imgSearch.setOnClickListener(this);
    }

    private void initList() {
        ideasAdapter = new HomeIdeasAdapter(getActivity(), arrayBrainIdeas, mSharedPrefsHelper.getUsername(),
                mCommonUtil, mNetworkService);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(ideasAdapter);
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

    private void callBrainIdeaApi() {
        if (!mCommonUtil.isInternetAvailable(getActivity())) {
            return;
        }

        // Now call web service using retrofit
        mNetworkService.doIdea(getActivity(),
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
            } else {
                txtNoData.setVisibility(View.GONE);
            }

            initList();

            ideasAdapter.notifyDataSetChanged();

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
            callBrainIdeaApi();
        }

        isFirstTime = false;
    }
}
