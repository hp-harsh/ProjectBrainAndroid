package hp.harsh.projectbrain.networks;

import java.util.Map;

import hp.harsh.projectbrain.models.LoginModel;
import hp.harsh.projectbrain.util.Constants;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {
    @POST("" + Constants.BRAIN_LOGIN)
    Observable<LoginModel> getUserLogin(@Body Map<String, String> options);

}
