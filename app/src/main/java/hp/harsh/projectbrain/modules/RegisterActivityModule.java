package hp.harsh.projectbrain.modules;

import dagger.Module;
import dagger.Provides;
import hp.harsh.projectbrain.activities.RegisterActivity;
import hp.harsh.projectbrain.annotations.MainActivityScope;


@Module
public class RegisterActivityModule {

    RegisterActivity registerActivity;

    public RegisterActivityModule(RegisterActivity registerActivity) {
        this.registerActivity = registerActivity;
    }

    @Provides
    @MainActivityScope
    public RegisterActivity registerActivity() {
        return registerActivity;
    }
}
