package hp.harsh.projectbrain.modules;

import android.content.Context;
import android.content.SharedPreferences;

import dagger.Module;
import dagger.Provides;
import hp.harsh.projectbrain.annotations.ApiServiceScope;
import hp.harsh.projectbrain.annotations.ApplicationQualifier;


@Module(includes = ContextModule.class)
public class SharedPreferenceModule {

    @Provides
    @ApiServiceScope
    public SharedPreferences sharedPreferences(@ApplicationQualifier Context context) {
        return context.getSharedPreferences("demo-prefs", Context.MODE_PRIVATE);
    }
}
