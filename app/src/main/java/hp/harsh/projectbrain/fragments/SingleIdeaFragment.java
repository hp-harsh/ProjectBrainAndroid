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

public class SingleIdeaFragment extends BaseFragment {
    private static final String TAG = SingleIdeaFragment.class.getSimpleName();

    private TextView mTxtTitle;

    private TextView txtIdeaTitle;
    private TextView txtContext;
    private TextView txtContent;
    private TextView txtPostedBy;

    private ImageView imgCite;
    private ImageView imgTodo;
    private ImageView imgFollow;

    public static SingleIdeaFragment getInstance() {
        SingleIdeaFragment frag = new SingleIdeaFragment();
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_single_idea, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);
    }

    private void init(View view) {
        mTxtTitle = view.findViewById(R.id.txtTitle);
        txtIdeaTitle = view.findViewById(R.id.txtIdeaTitle);
        txtContext = view.findViewById(R.id.txtContext);
        txtContent = view.findViewById(R.id.txtContent);
        txtPostedBy = view.findViewById(R.id.txtPostedBy);

        imgCite = view.findViewById(R.id.imgCite);
        imgTodo = view.findViewById(R.id.imgTodo);
        imgFollow = view.findViewById(R.id.imgFollow);

        mTxtTitle.setText(mResourceUtil.getString(R.string.idea));
    }
}
