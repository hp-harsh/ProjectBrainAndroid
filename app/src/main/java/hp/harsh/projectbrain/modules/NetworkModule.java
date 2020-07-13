package hp.harsh.projectbrain.modules;

import android.content.Context;

import java.io.File;

import dagger.Module;
import dagger.Provides;
import hp.harsh.projectbrain.annotations.ApiServiceScope;
import hp.harsh.projectbrain.annotations.ApplicationQualifier;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;


@Module(includes = ContextModule.class)
public class NetworkModule {

    @Provides
    @ApiServiceScope
    public File cacheFile(@ApplicationQualifier Context context) {
        return new File(context.getCacheDir(), "okhttp_cache");
    }

    @Provides
    @ApiServiceScope
    public Cache cache(File cacheFile) {
        cacheFile.mkdirs();
        return new Cache(cacheFile, 10 * 1000 * 1000); // 10 MB
    }

    @Provides
    @ApiServiceScope
    public HttpLoggingInterceptor interceptor() {
        HttpLoggingInterceptor interceptor =  new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Timber.i(message);
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Provides
    @ApiServiceScope
    public OkHttpClient okHttpClient(HttpLoggingInterceptor interceptor, Cache cache) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .cache(cache)
                .build();
    }
}
