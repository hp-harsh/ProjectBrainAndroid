package hp.harsh.projectbrain.modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import hp.harsh.projectbrain.annotations.ApiServiceScope;
import hp.harsh.projectbrain.annotations.ApplicationQualifier;

@Module
public class ContextModule {

    private Context context;

    public ContextModule(Context context) {
        this.context = context.getApplicationContext();
    }

    @Provides
    @ApiServiceScope
    @ApplicationQualifier
    public Context context() {
        return context;
    }
}
