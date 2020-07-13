package hp.harsh.projectbrain.fragments;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hp.harsh.projectbrain.R;
import hp.harsh.projectbrain.adapters.IdeasAdapter;
import hp.harsh.projectbrain.events.OnIdeaRemoved;
import hp.harsh.projectbrain.models.BrainIdeaModel;
import hp.harsh.projectbrain.models.NewIdeaModel;
import hp.harsh.projectbrain.networks.NetworkCallback;
import hp.harsh.projectbrain.networks.NetworkError;
import hp.harsh.projectbrain.networks.RequestParam;
import hp.harsh.projectbrain.util.ToastUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class BrainIdeasFragment extends BaseFragment implements NetworkCallback {
    private static final String TAG = BrainIdeasFragment.class.getSimpleName();

    private TextView mTxtTitle;
    private TextView txtNoData;

    private RecyclerView recyclerView;

    private IdeasAdapter ideasAdapter;
    private ArrayList<BrainIdeaModel.Datum> arrayBrainIdeas = new ArrayList<>();

    private final CompositeDisposable mDisposables = new CompositeDisposable();

    public static BrainIdeasFragment getInstance() {
        BrainIdeasFragment frag = new BrainIdeasFragment();
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ideas, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);
        initRxBusListener();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mDisposables.clear(); // do not send event after fragment has been destroyed
    }

    private void init(View view) {
        mTxtTitle = view.findViewById(R.id.txtTitle);
        txtNoData = view.findViewById(R.id.txtNoData);

        recyclerView = view.findViewById(R.id.recyclerView);

        mTxtTitle.setText(mResourceUtil.getString(R.string.brain_ideas));

        callBrainIdeaApi();

        initList();
    }

    private void initList() {
        ideasAdapter = new IdeasAdapter(getActivity(), arrayBrainIdeas,
                mSharedPrefsHelper.getUsername(), mCommonUtil, mNetworkService, mRxBus);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(ideasAdapter);
    }

    private void initRxBusListener() {
        mDisposables.add(mRxBus
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object object) throws Exception {

                        if (object instanceof OnIdeaRemoved) {
                            OnIdeaRemoved onIdeaRemoved = (OnIdeaRemoved) object;

                            if (onIdeaRemoved.getRemovedPosition() != -1) {
                                arrayBrainIdeas.remove(onIdeaRemoved.getRemovedPosition());

                                if (arrayBrainIdeas.size() == 0) {
                                    txtNoData.setVisibility(View.VISIBLE);
                                } else {
                                    txtNoData.setVisibility(View.GONE);
                                }

                                ideasAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                }));
    }

    private void callBrainIdeaApi() {
        Log.i(TAG, "callLoginApi");
        if (!mCommonUtil.isInternetAvailable(getActivity())) {
            return;
        }

        // Now call web service using retrofit
        mNetworkService.doBrainIdea(getActivity(),
                "" + mSharedPrefsHelper.getUsername(),
                false,
                this);
    }

    @Override
    public void onSuccess(Object networkResponse) {
        if (networkResponse instanceof BrainIdeaModel) {

            BrainIdeaModel responseModel = (BrainIdeaModel) networkResponse;

            arrayBrainIdeas.addAll(responseModel.getData());

            if (arrayBrainIdeas.size() == 0) {
                txtNoData.setVisibility(View.VISIBLE);
            } else {
                txtNoData.setVisibility(View.GONE);
            }

            ideasAdapter.notifyDataSetChanged();

        } else {
            ToastUtil.show(getActivity(), mResourceUtil.getString(R.string.toast_something_wrong));
        }
    }

    @Override
    public void onError(NetworkError networkError) {
        ToastUtil.show(getActivity(), "" + networkError.getMessage());
    }
}
