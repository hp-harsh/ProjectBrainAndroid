package hp.harsh.projectbrain.modules;

import dagger.Module;
import dagger.Provides;
import hp.harsh.projectbrain.activities.HomeActivity;
import hp.harsh.projectbrain.annotations.HomeActivityScope;


@Module
public class HomeActivityModule {

    HomeActivity homeActivity;

    public HomeActivityModule(HomeActivity homeActivity) {
        this.homeActivity = homeActivity;
    }

    @Provides
    @HomeActivityScope
    public HomeActivity homeActivity() {
        return homeActivity;
    }
}
