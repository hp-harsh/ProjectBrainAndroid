package hp.harsh.projectbrain.modules;

import dagger.Module;
import dagger.Provides;
import hp.harsh.projectbrain.fragments.BaseFragment;

@Module
public class FragmentModule {

    private BaseFragment mBaseFragment;

    public FragmentModule(BaseFragment baseFragment) {
        this.mBaseFragment = baseFragment;
    }
}
