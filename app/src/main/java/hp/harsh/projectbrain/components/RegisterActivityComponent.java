package hp.harsh.projectbrain.components;

import dagger.Component;
import hp.harsh.projectbrain.activities.RegisterActivity;
import hp.harsh.projectbrain.annotations.RegisterActivityScope;
import hp.harsh.projectbrain.modules.RegisterActivityModule;

@RegisterActivityScope
@Component(modules = RegisterActivityModule.class, dependencies = MyApplicationComponent.class)
public interface RegisterActivityComponent {

    void registerActivityInject(RegisterActivity registerActivity);
}
