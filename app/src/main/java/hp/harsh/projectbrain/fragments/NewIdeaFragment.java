package hp.harsh.projectbrain.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import hp.harsh.projectbrain.R;

public class NewIdeaFragment extends BaseFragment implements View.OnClickListener {
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

        mTxtTitle.setText("");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSubmit:
                break;
        }
    }
}
