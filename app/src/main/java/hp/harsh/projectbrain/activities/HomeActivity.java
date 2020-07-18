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
import hp.harsh.projectbrain.fragments.BrainIdeasFragment;
import hp.harsh.projectbrain.fragments.BrainTodoFragment;
import hp.harsh.projectbrain.fragments.BrainUpdateProfileFragment;
import hp.harsh.projectbrain.fragments.CiteIdeaFragment;
import hp.harsh.projectbrain.fragments.IdeasFragment;
import hp.harsh.projectbrain.fragments.NewIdeaFragment;
import hp.harsh.projectbrain.fragments.ProfileFragment;
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
    private ImageButton imgSearch;
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
        imgSearch = findViewById(R.id.imgSearch);
        imgHome = findViewById(R.id.imgHome);

        imgProfile.setOnClickListener(this);
        imgNewIdea.setOnClickListener(this);
        imgSearch.setOnClickListener(this);
        imgHome.setOnClickListener(this);

        replaceFragment(IdeasFragment.getInstance());
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frameContainer);

        if (fragment instanceof BrainIdeasFragment ||
                fragment instanceof BrainTodoFragment ||
                fragment instanceof BrainUpdateProfileFragment ||
                fragment instanceof CiteIdeaFragment) {
            removeFragment(fragment);
        } else if (fragment instanceof IdeasFragment) {
            // Quit app if user double tap back pressed
            mRxUtil.quitApp(HomeActivity.this);
        } else {
            replaceFragment(IdeasFragment.getInstance());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgHome:
                replaceFragment(IdeasFragment.getInstance());
                break;
            case R.id.imgNewIdea:
                replaceFragment(NewIdeaFragment.getInstance());
                break;
            case R.id.imgSearch:
                break;
            case R.id.imgProfile:
                replaceFragment(ProfileFragment.getInstance());
                break;
        }
    }

    public void addFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().add(R.id.frameContainer,
                fragment, fragment.getClass().getSimpleName()).commit();
    }

    public void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer,
                fragment, fragment.getClass().getSimpleName()).commit();
    }

    public void removeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
    }
}
