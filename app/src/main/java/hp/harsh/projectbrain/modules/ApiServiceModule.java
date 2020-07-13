package hp.harsh.projectbrain.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import hp.harsh.projectbrain.annotations.ApiServiceScope;
import hp.harsh.projectbrain.networks.ApiService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@Module(includes = NetworkModule.class)
public class ApiServiceModule {

    private String BASE_URL = "";

    @Provides
    @ApiServiceScope
    public Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        //gsonBuilder.registerTypeAdapter(DateTime.class, new DateTimeConverter());
        return new Gson();
    }

    @Provides
    @ApiServiceScope
    public Retrofit retrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .build();
    }

    @Provides
    @ApiServiceScope
    public ApiService apiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }
}
