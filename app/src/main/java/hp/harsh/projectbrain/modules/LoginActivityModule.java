package hp.harsh.projectbrain.modules;

import dagger.Module;
import dagger.Provides;
import hp.harsh.projectbrain.activities.LoginActivity;
import hp.harsh.projectbrain.annotations.MainActivityScope;

@Module
public class LoginActivityModule {

    LoginActivity loginActivity;

    public LoginActivityModule(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
    }

    @Provides
    @MainActivityScope
    public LoginActivity loginActivity() {
        return loginActivity;
    }

    /*@Provides
    @MainActivityScope
    public MoviesAdapter moviesAdapter(Picasso picasso) {
        return new MoviesAdapter(mainActivity, picasso);
    }*/
}
