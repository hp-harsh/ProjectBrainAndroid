package hp.harsh.projectbrain.modules;

import dagger.Module;
import dagger.Provides;
import hp.harsh.projectbrain.activities.MainActivity;
import hp.harsh.projectbrain.annotations.MainActivityScope;


@Module
public class MainActivityModule {

    MainActivity mainActivity;

    public MainActivityModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    @MainActivityScope
    public MainActivity mainActivity() {
        return mainActivity;
    }

    /*@Provides
    @MainActivityScope
    public MoviesAdapter moviesAdapter(Picasso picasso) {
        return new MoviesAdapter(mainActivity, picasso);
    }*/
}
