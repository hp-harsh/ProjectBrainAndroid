package hp.harsh.projectbrain.components;

import android.content.SharedPreferences;

import dagger.Component;
import hp.harsh.projectbrain.annotations.ApiServiceScope;
import hp.harsh.projectbrain.modules.ApiServiceModule;
import hp.harsh.projectbrain.modules.SharedPreferenceModule;
import hp.harsh.projectbrain.modules.UtilModule;
import hp.harsh.projectbrain.networks.ApiService;
import hp.harsh.projectbrain.networks.NetworkService;
import hp.harsh.projectbrain.util.ResourceUtil;
import hp.harsh.projectbrain.util.RxBus;
import hp.harsh.projectbrain.util.RxUtil;
import hp.harsh.projectbrain.util.SharedPrefsHelper;

/**
 * Created by harsh on 22/3/18.
 */

@ApiServiceScope
@Component(modules = {ApiServiceModule.class, SharedPreferenceModule.class, UtilModule.class})
public interface MyApplicationComponent {

    ResourceUtil getResourceUtil();

    RxBus getRxBus();

    RxUtil getRxUtil();

    ApiService getApiService();

    SharedPreferences getSharedPreferences();

    SharedPrefsHelper getSharedPrefsHelper();

    NetworkService getNetworkService();
}