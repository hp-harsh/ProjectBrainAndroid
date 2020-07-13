package hp.harsh.projectbrain.components;

import android.content.SharedPreferences;

import dagger.Component;
import hp.harsh.projectbrain.activities.HomeActivity;
import hp.harsh.projectbrain.annotations.HomeActivityScope;
import hp.harsh.projectbrain.modules.HomeActivityModule;
import hp.harsh.projectbrain.networks.ApiService;
import hp.harsh.projectbrain.networks.NetworkService;
import hp.harsh.projectbrain.util.ResourceUtil;
import hp.harsh.projectbrain.util.RxBus;
import hp.harsh.projectbrain.util.RxUtil;
import hp.harsh.projectbrain.util.SharedPrefsHelper;

@HomeActivityScope
@Component(modules = HomeActivityModule.class, dependencies = MyApplicationComponent.class)
public interface HomeActivityComponent {

    void homeActivityInject(HomeActivity homeActivity);

    HomeActivity getHomeActivity();

    ResourceUtil getResourceUtil();

    RxBus getRxBus();

    RxUtil getRxUtil();

    ApiService getApiService();

    SharedPreferences getSharedPreferences();

    SharedPrefsHelper getSharedPrefsHelper();

    NetworkService getNetworkService();

}
