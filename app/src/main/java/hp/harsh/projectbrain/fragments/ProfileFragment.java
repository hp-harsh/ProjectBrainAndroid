package hp.harsh.projectbrain.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import hp.harsh.projectbrain.R;
import hp.harsh.projectbrain.activities.LoginActivity;
import hp.harsh.projectbrain.activities.RegisterActivity;

public class ProfileFragment extends BaseFragment implements View.OnClickListener {
    private static final String TAG = ProfileFragment.class.getSimpleName();

    private TextView mTxtTitle;

    private LinearLayout lnrBrainIdeas;
    private LinearLayout lnrBrainTodo;
    private LinearLayout lnrBrainUpdateProfile;
    private LinearLayout lnrLogout;

    public static ProfileFragment getInstance() {
        ProfileFragment frag = new ProfileFragment();
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);
    }

    private void init(View view) {
        mTxtTitle = view.findViewById(R.id.txtTitle);

        lnrBrainIdeas = view.findViewById(R.id.lnrBrainIdeas);
        lnrBrainTodo = view.findViewById(R.id.lnrBrainTodo);
        lnrBrainUpdateProfile = view.findViewById(R.id.lnrBrainUpdateProfile);
        lnrLogout = view.findViewById(R.id.lnrLogout);

        lnrBrainIdeas.setOnClickListener(this);
        lnrBrainTodo.setOnClickListener(this);
        lnrBrainUpdateProfile.setOnClickListener(this);
        lnrLogout.setOnClickListener(this);

        mTxtTitle.setText("");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lnrBrainIdeas:
                break;

            case R.id.lnrBrainTodo:
                break;

            case R.id.lnrBrainUpdateProfile:
                break;

            case R.id.lnrLogout:
                break;
        }
    }
}
