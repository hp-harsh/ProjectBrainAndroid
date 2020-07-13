package hp.harsh.projectbrain.networks;

public interface NetworkCallback {

    void onSuccess(Object networkResponse);

    void onError(NetworkError networkError);
}
