package hp.harsh.projectbrain.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hp.harsh.projectbrain.R;
import hp.harsh.projectbrain.activities.HomeActivity;
import hp.harsh.projectbrain.fragments.CiteIdeaFragment;
import hp.harsh.projectbrain.models.BrainIdeaModel;
import hp.harsh.projectbrain.models.FollowModel;
import hp.harsh.projectbrain.models.TodoModel;
import hp.harsh.projectbrain.networks.NetworkCallback;
import hp.harsh.projectbrain.networks.NetworkError;
import hp.harsh.projectbrain.networks.NetworkService;
import hp.harsh.projectbrain.networks.RequestParam;
import hp.harsh.projectbrain.util.CommonUtil;
import hp.harsh.projectbrain.util.ToastUtil;

public class HomeIdeasAdapter extends RecyclerView.Adapter<HomeIdeasAdapter.ViewHolder> implements NetworkCallback {

    private ArrayList<BrainIdeaModel.Datum> arrayUserIdeas;
    private String username;
    private Context context;
    private CommonUtil mCommonUtil;
    private NetworkService mNetworkService;

    public HomeIdeasAdapter(Context context, ArrayList<BrainIdeaModel.Datum> arrayUserIdeas,
                            String username, CommonUtil mCommonUtil, NetworkService mNetworkService) {
        this.context = context;
        this.arrayUserIdeas = arrayUserIdeas;
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
        final BrainIdeaModel.Datum userIdeaModel = arrayUserIdeas.get(position);

        holder.txtTitle.setText("Title: " + userIdeaModel.getTitle());
        holder.txtContext.setText("Context: " + userIdeaModel.getContext());
        holder.txtContent.setText("Content: " + userIdeaModel.getContent());

        if (userIdeaModel.getAuthor().getUsername().equals("" + username)) {
            holder.imgTodo.setVisibility(View.GONE);
            holder.imgFollow.setVisibility(View.GONE);
            holder.imgCite.setVisibility(View.GONE);

            holder.txtPostedBy.setText("Author: You");

        } else {
            holder.imgTodo.setVisibility(View.VISIBLE);
            holder.imgFollow.setVisibility(View.VISIBLE);
            holder.imgCite.setVisibility(View.VISIBLE);

            holder.txtPostedBy.setText("Author: " + userIdeaModel.getAuthor().getUsername());
        }

        holder.imgCite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HomeActivity) context).addFragment(CiteIdeaFragment
                        .getInstance("" + userIdeaModel.getId()
                                , "" + userIdeaModel.getTitle()));
            }
        });

        holder.imgTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callTodoApi("" + userIdeaModel.getId());
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
        return arrayUserIdeas.size();
    }

    @Override
    public void onSuccess(Object networkResponse) {
        if (networkResponse instanceof TodoModel) {

            TodoModel responseModel = (TodoModel) networkResponse;

            Toast.makeText(
                    context,
                    R.string.success,
                    Toast.LENGTH_LONG
            ).show();

        } else if (networkResponse instanceof FollowModel) {

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

        public ImageView imgCite;
        public ImageView imgTodo;
        public ImageView imgFollow;

        public ViewHolder(View itemView) {
            super(itemView);
            this.txtTitle = (TextView) itemView.findViewById(R.id.txtIdeaTitle);
            this.txtContext = (TextView) itemView.findViewById(R.id.txtContext);
            this.txtContent = (TextView) itemView.findViewById(R.id.txtContent);
            this.txtPostedBy = (TextView) itemView.findViewById(R.id.txtPostedBy);

            this.imgCite = (ImageView) itemView.findViewById(R.id.imgCite);
            this.imgTodo = (ImageView) itemView.findViewById(R.id.imgTodo);
            this.imgFollow = (ImageView) itemView.findViewById(R.id.imgFollow);
        }
    }

    private void callTodoApi(String ideaId) {
        if (!mCommonUtil.isInternetAvailable(context)) {
            return;
        }

        // Now call web service using retrofit
        mNetworkService.doTodo(context,
                RequestParam.paramTodo(username, ideaId),
                false,
                this);
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