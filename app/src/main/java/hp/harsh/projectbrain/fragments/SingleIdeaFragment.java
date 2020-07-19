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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import hp.harsh.projectbrain.R;
import hp.harsh.projectbrain.events.OnIdeaRemoved;
import hp.harsh.projectbrain.models.BrainIdeaModel;
import hp.harsh.projectbrain.models.IdeaRemovedModel;
import hp.harsh.projectbrain.models.NewIdeaModel;
import hp.harsh.projectbrain.networks.NetworkCallback;
import hp.harsh.projectbrain.networks.NetworkError;
import hp.harsh.projectbrain.util.ToastUtil;

public class SingleIdeaFragment extends BaseFragment implements NetworkCallback {
    private static final String TAG = SingleIdeaFragment.class.getSimpleName();

    private TextView mTxtTitle;

    private TextView txtIdeaTitle;
    private TextView txtContext;
    private TextView txtContent;
    private TextView txtPostedBy;

    private ImageView imgCite;
    private ImageView imgTodo;
    private ImageView imgFollow;

    private String ideaId;

    public static SingleIdeaFragment getInstance(String ideaId) {
        SingleIdeaFragment frag = new SingleIdeaFragment();
        frag.ideaId = ideaId;
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

    @Override
    public void onSuccess(Object networkResponse) {
        if (networkResponse instanceof NewIdeaModel) {

            NewIdeaModel responseModel = (NewIdeaModel) networkResponse;

            txtIdeaTitle.setText("Title: " + responseModel.getTitle());
            txtContext.setText("Context: " + responseModel.getContext());
            txtContent.setText("Content: " + responseModel.getContent());

            txtPostedBy.setText("Author: " + responseModel.getAuthor().getUsername());

        } else {
            ToastUtil.show(getActivity(), mResourceUtil.getString(R.string.toast_something_wrong));
        }
    }

    @Override
    public void onError(NetworkError networkError) {
        ToastUtil.show(getActivity(), "" + networkError.getMessage());
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

        // Not required to show action buttons
        imgCite.setVisibility(View.GONE);
        imgTodo.setVisibility(View.GONE);
        imgFollow.setVisibility(View.GONE);

        mTxtTitle.setText(mResourceUtil.getString(R.string.idea));

        callRemoveApi("" + ideaId);
    }

    private void callRemoveApi(String id) {
        if (!mCommonUtil.isInternetAvailable(getActivity())) {
            return;
        }

        // Now call web service using retrofit
        mNetworkService.doGetIdea(getActivity(),
                "" + id,
                false,
                this);
    }
}
