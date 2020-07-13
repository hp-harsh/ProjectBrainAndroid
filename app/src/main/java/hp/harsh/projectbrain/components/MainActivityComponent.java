package hp.harsh.projectbrain.components;

import dagger.Component;
import hp.harsh.projectbrain.activities.MainActivity;
import hp.harsh.projectbrain.annotations.MainActivityScope;
import hp.harsh.projectbrain.modules.MainActivityModule;

@MainActivityScope
@Component(modules = MainActivityModule.class, dependencies = MyApplicationComponent.class)
public interface MainActivityComponent {

    void mainActivityInject(MainActivity mainActivity);

    /*ApiService apiService();

    MoviesAdapter moviesAdapter();*/
}
