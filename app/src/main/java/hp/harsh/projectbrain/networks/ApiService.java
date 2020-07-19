package hp.harsh.projectbrain.networks;

import java.util.Map;

import hp.harsh.projectbrain.models.BrainIdeaModel;
import hp.harsh.projectbrain.models.BrainTodoModel;
import hp.harsh.projectbrain.models.FollowModel;
import hp.harsh.projectbrain.models.IdeaRemovedModel;
import hp.harsh.projectbrain.models.LoginModel;
import hp.harsh.projectbrain.models.NewIdeaModel;
import hp.harsh.projectbrain.models.RegistrationModel;
import hp.harsh.projectbrain.models.TodoModel;
import hp.harsh.projectbrain.models.UpdateProfileModel;
import hp.harsh.projectbrain.util.Constants;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @POST("" + Constants.BRAIN_LOGIN)
    Observable<LoginModel> getUserLogin(@Body Map<String, String> options);

    @POST("" + Constants.BRAIN_REGISTRATION)
    Observable<RegistrationModel> getUserRegistration(@Body Map<String, String> options);

    @POST("" + Constants.BRAIN_UPDATE)
    Observable<UpdateProfileModel> getUserUpdateProfile(@Body Map<String, String> options);

    @POST("" + Constants.BRAIN_NEW_IDEA)
    Observable<NewIdeaModel> getNewIdea(@Body Map<String, String> options);

    @GET("" + Constants.BRAIN_BRAIN_IDEA)
    Observable<BrainIdeaModel> getBrainIdea(@Path("username") String username);

    @GET("" + Constants.BRAIN_BRAIN_TODO)
    Observable<BrainTodoModel> getBrainTodo(@Path("username") String username);

    @GET("" + Constants.BRAIN_IDEA)
    Observable<BrainIdeaModel> getIdea();

    @POST("" + Constants.BRAIN_FOLLOW)
    Observable<FollowModel> getFollow(@Body Map<String, String> options);

    @POST("" + Constants.BRAIN_TODO)
    Observable<TodoModel> getTodo(@Body Map<String, String> options);

    @DELETE("" + Constants.BRAIN_IDEA_DELETE)
    Observable<IdeaRemovedModel> getIdeaRemove(@Query("id") String id);

    @GET("" + Constants.BRAIN_SINGLE_IDEA)
    Observable<NewIdeaModel> getIdea(@Query("id") String id);

}
