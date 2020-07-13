package hp.harsh.projectbrain.fragments;

import android.os.Bundle;
import android.util.Log;
import android.util.Printer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hp.harsh.projectbrain.R;
import hp.harsh.projectbrain.adapters.IdeasAdapter;
import hp.harsh.projectbrain.adapters.TodoAdapter;
import hp.harsh.projectbrain.models.BrainIdeaModel;
import hp.harsh.projectbrain.models.BrainTodoModel;
import hp.harsh.projectbrain.networks.NetworkCallback;
import hp.harsh.projectbrain.networks.NetworkError;
import hp.harsh.projectbrain.util.ToastUtil;

public class BrainTodoFragment extends BaseFragment implements NetworkCallback {
    private static final String TAG = BrainTodoFragment.class.getSimpleName();

    private TextView mTxtTitle;
    private TextView txtNoData;

    private ImageView imgBack;

    private RecyclerView recyclerView;

    private TodoAdapter todoAdapter;

    private ArrayList<BrainTodoModel.Datum> arrayBrainTodo = new ArrayList<>();

    public static BrainTodoFragment getInstance() {
        BrainTodoFragment frag = new BrainTodoFragment();
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_todo, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);
    }

    private void init(View view) {
        mTxtTitle = view.findViewById(R.id.txtTitle);
        txtNoData = view.findViewById(R.id.txtNoData);

        recyclerView = view.findViewById(R.id.recyclerView);

        mTxtTitle.setText(mResourceUtil.getString(R.string.brain_todo));

        initList();

        callBrainIdeaApi();
    }



    private void initList() {
        todoAdapter = new TodoAdapter(getActivity(), arrayBrainTodo,
                mSharedPrefsHelper.getUsername(), mCommonUtil, mNetworkService);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(todoAdapter);
    }

    private void callBrainIdeaApi() {
        Log.i(TAG, "callLoginApi");
        if (!mCommonUtil.isInternetAvailable(getActivity())) {
            return;
        }

        // Now call web service using retrofit
        mNetworkService.doBrainTodo(getActivity(),
                "" + mSharedPrefsHelper.getUsername(),
                false,
                this);
    }

    @Override
    public void onSuccess(Object networkResponse) {
        if (networkResponse instanceof BrainTodoModel) {

            BrainTodoModel responseModel = (BrainTodoModel) networkResponse;

            arrayBrainTodo.addAll(responseModel.getData());

            if (arrayBrainTodo.size() == 0) {
                txtNoData.setVisibility(View.VISIBLE);
            } else {
                txtNoData.setVisibility(View.GONE);
            }

            todoAdapter.notifyDataSetChanged();

        } else {
            ToastUtil.show(getActivity(), mResourceUtil.getString(R.string.toast_something_wrong));
        }
    }

    @Override
    public void onError(NetworkError networkError) {
        ToastUtil.show(getActivity(), "" + networkError.getMessage());
    }
}
