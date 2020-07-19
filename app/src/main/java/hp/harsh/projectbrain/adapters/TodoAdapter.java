package hp.harsh.projectbrain.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import hp.harsh.projectbrain.R;
import hp.harsh.projectbrain.activities.HomeActivity;
import hp.harsh.projectbrain.fragments.CiteIdeaFragment;
import hp.harsh.projectbrain.fragments.SingleIdeaFragment;
import hp.harsh.projectbrain.models.BrainTodoModel;
import hp.harsh.projectbrain.models.FollowModel;
import hp.harsh.projectbrain.networks.NetworkCallback;
import hp.harsh.projectbrain.networks.NetworkError;
import hp.harsh.projectbrain.networks.NetworkService;
import hp.harsh.projectbrain.networks.RequestParam;
import hp.harsh.projectbrain.util.CommonUtil;
import hp.harsh.projectbrain.util.ToastUtil;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> implements NetworkCallback {

    private ArrayList<BrainTodoModel.Datum> arrayUserTodos;
    private Context context;
    private String username;
    private CommonUtil mCommonUtil;
    private NetworkService mNetworkService;

    public TodoAdapter(Context context, ArrayList<BrainTodoModel.Datum> arrayUserTodos,
                       String username, CommonUtil mCommonUtil, NetworkService mNetworkService) {
        this.arrayUserTodos = arrayUserTodos;
        this.context = context;
        this.username = username;
        this.mCommonUtil = mCommonUtil;
        this.mNetworkService = mNetworkService;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.view_idea, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final BrainTodoModel.Datum userIdeaModel = arrayUserTodos.get(position);

        holder.txtTitle.setText("Title: " + userIdeaModel.getTitle());
        holder.txtContext.setText("Context: " + userIdeaModel.getContext());
        holder.txtContent.setText("Content: " + userIdeaModel.getContent());
        holder.txtPostedBy.setText("Author: " + userIdeaModel.getAuthor().getUsername());

        holder.imgTodo.setVisibility(View.GONE);

        if (!("" + userIdeaModel.getOriginalId()).trim().equals("null")) {
            holder.txtContext.setTextColor(((HomeActivity) context).getColor(R.color.citeColor));
        }

        holder.txtContext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!("" + userIdeaModel.getOriginalId()).trim().equals("null")) {
                    ((HomeActivity) context).addFragment(SingleIdeaFragment
                            .getInstance("" + userIdeaModel.getOriginalId()));
                }
            }
        });

        holder.imgCite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HomeActivity) context).addFragment(CiteIdeaFragment
                        .getInstance("" + userIdeaModel.getId()
                                , "" + userIdeaModel.getTitle()));
            }
        });

        holder.imgFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callFollowApi("" + userIdeaModel.getAuthor().getUsername());
            }
        });
    }


    @Override
    public int getItemCount() {
        return arrayUserTodos.size();
    }

    @Override
    public void onSuccess(Object networkResponse) {
        if (networkResponse instanceof FollowModel) {

            FollowModel responseModel = (FollowModel) networkResponse;

            Toast.makeText(
                    context,
                    R.string.success,
                    Toast.LENGTH_LONG
            ).show();

        } else {
            ToastUtil.show(context, context.getString(R.string.toast_something_wrong));
        }
    }

    @Override
    public void onError(NetworkError networkError) {
        ToastUtil.show(context, "" + networkError.getMessage());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTitle;
        public TextView txtContext;
        public TextView txtContent;
        public TextView txtPostedBy;

        public ImageButton imgCite;
        public ImageButton imgTodo;
        public ImageButton imgFollow;

        public ViewHolder(View itemView) {
            super(itemView);
            this.txtTitle = (TextView) itemView.findViewById(R.id.txtIdeaTitle);
            this.txtContext = (TextView) itemView.findViewById(R.id.txtContext);
            this.txtContent = (TextView) itemView.findViewById(R.id.txtContent);
            this.txtPostedBy = (TextView) itemView.findViewById(R.id.txtPostedBy);

            this.imgCite = (ImageButton) itemView.findViewById(R.id.imgCite);
            this.imgTodo = (ImageButton) itemView.findViewById(R.id.imgTodo);
            this.imgFollow = (ImageButton) itemView.findViewById(R.id.imgFollow);
        }
    }

    private void callFollowApi(String usernameToBeFollowed) {
        if (!mCommonUtil.isInternetAvailable(context)) {
            return;
        }

        // Now call web service using retrofit
        mNetworkService.doFollow(context,
                RequestParam.paramFollow(username, usernameToBeFollowed),
                false,
                this);
    }
}