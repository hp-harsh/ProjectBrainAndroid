package hp.harsh.projectbrain.components;


import dagger.Component;
import hp.harsh.projectbrain.annotations.FragmentScope;
import hp.harsh.projectbrain.fragments.BaseFragment;
import hp.harsh.projectbrain.modules.FragmentModule;

@FragmentScope
@Component(dependencies = {HomeActivityComponent.class}, modules = FragmentModule.class)
public interface FragmentComponent {

    void injectBaseFragment(BaseFragment baseFragment);

}
