package hp.harsh.projectbrain.activities;

import android.os.Bundle;

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

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = HomeActivity.class.getSimpleName();

    private static HomeActivity homeActivity;

    private static HomeActivityComponent component;

    @Inject
    CommonUtil mCommonUtil;

    @Inject
    RxUtil mRxUtil;

    private TabLayout mTabLayout;

    public static HomeActivity getInstance() {
        return homeActivity;
    }

    public static HomeActivityComponent getHomeAppicationComponent() {
        return component;
    }

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
    }
}
