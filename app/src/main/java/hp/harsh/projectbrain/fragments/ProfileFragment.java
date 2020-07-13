package hp.harsh.projectbrain.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
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

    private ImageButton imgIdeas;
    private ImageButton imgTodo;
    private ImageButton imgUpdateProfile;
    private ImageButton imgLogout;

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

        imgIdeas = view.findViewById(R.id.imgIdeas);
        imgTodo = view.findViewById(R.id.imgTodo);
        imgUpdateProfile = view.findViewById(R.id.imgUpdateProfile);
        imgLogout = view.findViewById(R.id.imgLogout);

        imgIdeas.setOnClickListener(this);
        imgTodo.setOnClickListener(this);
        imgUpdateProfile.setOnClickListener(this);
        imgLogout.setOnClickListener(this);

        mTxtTitle.setText(mResourceUtil.getString(R.string.profile));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgIdeas:
                Log.i(TAG, "onClick: " + imgIdeas);
                addFragment(BrainIdeasFragment.getInstance());
                break;

            case R.id.imgTodo:
                addFragment(BrainTodoFragment.getInstance());
                break;

            case R.id.imgUpdateProfile:
                addFragment(BrainUpdateProfileFragment.getInstance());
                break;

            case R.id.imgLogout:
                mSharedPrefsHelper.clear();

                startActivity(new Intent(mActivityContext, LoginActivity.class));
                mActivityContext.finish();
                break;
        }
    }
}
