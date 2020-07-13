package hp.harsh.projectbrain.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import javax.inject.Inject;

import hp.harsh.projectbrain.R;
import hp.harsh.projectbrain.activities.HomeActivity;
import hp.harsh.projectbrain.components.DaggerFragmentComponent;
import hp.harsh.projectbrain.components.FragmentComponent;
import hp.harsh.projectbrain.modules.FragmentModule;
import hp.harsh.projectbrain.networks.NetworkService;
import hp.harsh.projectbrain.util.CommonUtil;
import hp.harsh.projectbrain.util.ResourceUtil;
import hp.harsh.projectbrain.util.RxBus;
import hp.harsh.projectbrain.util.SharedPrefsHelper;

public class BaseFragment extends Fragment {

    @Inject
    HomeActivity mActivityContext;

    @Inject
    NetworkService mNetworkService;

    @Inject
    SharedPrefsHelper mSharedPrefsHelper;

    @Inject
    ResourceUtil mResourceUtil;

    @Inject
    CommonUtil mCommonUtil;

    @Inject
    RxBus mRxBus;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize dagger component for dashboard fragment
        FragmentComponent component = DaggerFragmentComponent.builder()
                .fragmentModule(new FragmentModule(this))
                .homeActivityComponent(HomeActivity.getInstance().getHomeAppicationComponent())
                .build();

        component.injectBaseFragment(this);

        // Hide keyboard if visible
        mCommonUtil.hideSoftKeyboard(mActivityContext);
    }

    public void addFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.frameContainer,
                fragment, fragment.getClass().getSimpleName()).commit();
    }

    public void replaceFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer,
                fragment, fragment.getClass().getSimpleName()).commit();
    }

    public void removeFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction().remove(fragment).commit();
    }
}
