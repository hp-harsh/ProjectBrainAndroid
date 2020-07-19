package hp.harsh.projectbrain.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import hp.harsh.projectbrain.events.OnIdeaRemoved;
import hp.harsh.projectbrain.fragments.SingleIdeaFragment;
import hp.harsh.projectbrain.models.BrainIdeaModel;
import hp.harsh.projectbrain.models.FollowModel;
import hp.harsh.projectbrain.models.IdeaRemovedModel;
import hp.harsh.projectbrain.networks.NetworkCallback;
import hp.harsh.projectbrain.networks.NetworkError;
import hp.harsh.projectbrain.networks.NetworkService;
import hp.harsh.projectbrain.networks.RequestParam;
import hp.harsh.projectbrain.util.CommonUtil;
import hp.harsh.projectbrain.util.RxBus;
import hp.harsh.projectbrain.util.ToastUtil;

public class IdeasAdapter extends RecyclerView.Adapter<IdeasAdapter.ViewHolder> implements NetworkCallback {

    private ArrayList<BrainIdeaModel.Datum> arrayUserIdeas;
    private String username;
    private Context context;
    private CommonUtil mCommonUtil;
    private NetworkService mNetworkService;
    private RxBus mRxBus;

    private int selectedPosition = -1;

    public IdeasAdapter(Context context, ArrayList<BrainIdeaModel.Datum> arrayUserIdeas
            , String username, CommonUtil mCommonUtil, NetworkService mNetworkService, RxBus mRxBus) {
        this.context = context;
        this.arrayUserIdeas = arrayUserIdeas;
        this.username = username;
        this.mCommonUtil = mCommonUtil;
        this.mNetworkService = mNetworkService;
        this.mRxBus = mRxBus;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.view_brain_idea, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final BrainIdeaModel.Datum userIdeaModel = arrayUserIdeas.get(position);

        holder.txtTitle.setText("Title: " + userIdeaModel.getTitle());
        holder.txtContext.setText("Context: " + userIdeaModel.getContext());
        holder.txtContent.setText("Content: " + userIdeaModel.getContent());

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

        if (userIdeaModel.getAuthor().getUsername().equals("" + username)) {
            holder.txtPostedBy.setText("Author: You");

        } else {
            holder.txtPostedBy.setText("Author: " + userIdeaModel.getAuthor().getUsername());
        }

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition = position;
                callRemoveApi("" + userIdeaModel.getId());
            }
        });
    }


    @Override
    public int getItemCount() {
        return arrayUserIdeas.size();
    }

    @Override
    public void onSuccess(Object networkResponse) {
        if (networkResponse instanceof IdeaRemovedModel) {

            IdeaRemovedModel responseModel = (IdeaRemovedModel) networkResponse;

            mRxBus.send(new OnIdeaRemoved(selectedPosition));

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

        public ImageView imgDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            this.txtTitle = (TextView) itemView.findViewById(R.id.txtIdeaTitle);
            this.txtContext = (TextView) itemView.findViewById(R.id.txtContext);
            this.txtContent = (TextView) itemView.findViewById(R.id.txtContent);
            this.txtPostedBy = (TextView) itemView.findViewById(R.id.txtPostedBy);

            this.imgDelete = (ImageView) itemView.findViewById(R.id.imgDelete);
        }
    }

    private void callRemoveApi(String id) {
        if (!mCommonUtil.isInternetAvailable(context)) {
            return;
        }

        // Now call web service using retrofit
        mNetworkService.doRemove(context,
                "" + id,
                false,
                this);
    }
}