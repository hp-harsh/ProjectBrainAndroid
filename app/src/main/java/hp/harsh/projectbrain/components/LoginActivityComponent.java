package hp.harsh.projectbrain.components;

import dagger.Component;
import hp.harsh.projectbrain.activities.LoginActivity;
import hp.harsh.projectbrain.annotations.LoginActivityScope;
import hp.harsh.projectbrain.modules.LoginActivityModule;

@LoginActivityScope
@Component(modules = LoginActivityModule.class, dependencies = MyApplicationComponent.class)
public interface LoginActivityComponent {

    void loginActivityInject(LoginActivity loginActivity);
}
