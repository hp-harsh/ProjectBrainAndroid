package hp.harsh.projectbrain.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import hp.harsh.projectbrain.R;

public class BrainTodoFragment extends BaseFragment implements View.OnClickListener {
    private static final String TAG = BrainTodoFragment.class.getSimpleName();

    private TextView mTxtTitle;

    private ImageView imgBack;

    private RecyclerView recyclerView;

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

        imgBack = view.findViewById(R.id.imgBack);

        recyclerView = view.findViewById(R.id.recyclerView);

        mTxtTitle.setText("");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBack:
                break;
        }
    }

    private void callRegistrationApi() {
        /*Log.i(TAG, "callLoginApi");
        if (!mCommonUtil.isInternetAvailable(RegisterActivity.this)) {
            return;
        }*/

        /*// Now call web service using retrofit
        mNetworkService.doUserSignIn(RegisterActivity.this,
                RequestParam.paramUserLogin("" + mEdtEmail.getText().toString(), "" + mEdtPassword.getText().toString()),
                false,
                this);*/
    }
}
