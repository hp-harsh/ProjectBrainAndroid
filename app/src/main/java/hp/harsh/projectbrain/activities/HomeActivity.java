package hp.harsh.projectbrain.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

import javax.inject.Inject;

import hp.harsh.projectbrain.MyApplication;
import hp.harsh.projectbrain.R;
import hp.harsh.projectbrain.components.DaggerHomeActivityComponent;
import hp.harsh.projectbrain.components.HomeActivityComponent;
import hp.harsh.projectbrain.modules.HomeActivityModule;
import hp.harsh.projectbrain.util.CommonUtil;
import hp.harsh.projectbrain.util.RxUtil;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = HomeActivity.class.getSimpleName();

    private static HomeActivity homeActivity;

    private static HomeActivityComponent component;

    @Inject
    CommonUtil mCommonUtil;

    @Inject
    RxUtil mRxUtil;

    public static HomeActivity getInstance() {
        return homeActivity;
    }

    public static HomeActivityComponent getHomeAppicationComponent() {
        return component;
    }

    private ImageButton imgProfile;
    private ImageButton imgNewIdea;
    private ImageButton imgHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        homeActivity = this;

        component = DaggerHomeActivityComponent.builder()
                .homeActivityModule(new HomeActivityModule(HomeActivity.this))
                .myApplicationComponent(MyApplication.getInstance().getApplicationComponent())
                .build();

        component.homeActivityInject(HomeActivity.this);

        init();
    }

    private void init() {
        imgProfile = findViewById(R.id.imgProfile);
        imgNewIdea = findViewById(R.id.imgNewIdea);
        imgHome = findViewById(R.id.imgHome);

        imgProfile.setOnClickListener(this);
        imgNewIdea.setOnClickListener(this);
        imgHome.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgHome:
                break;
            case R.id.imgNewIdea:
                break;
            case R.id.imgProfile:
                break;
        }
    }
}
