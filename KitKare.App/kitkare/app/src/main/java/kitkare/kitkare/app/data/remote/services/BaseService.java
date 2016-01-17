package kitkare.kitkare.app.data.remote.services;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import kitkare.kitkare.app.data.remote.models.UserData;

public abstract class BaseService {
    protected OkHttpClient client;
    protected final String AUTHORIZATION = "Authorization";
    protected final String CONTENT_TYPE = "Content-Type";
    protected final String APPLICATION_JSON = "application/json";

    protected BaseService(){
        this.client = new OkHttpClient();
    }

    public void get(String url, Callback callback) throws Exception {
        Request request = new Request.Builder()
                .url(url)
                .build();

        this.client.newCall(request).enqueue(callback);
    }

    public void authGet(String url, Callback callback) throws Exception {
        Request request = new Request.Builder()
                .header(AUTHORIZATION, "Bearer " + UserData.getToken())
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .url(url)
                .build();
        this.client.newCall(request).enqueue(callback);
    }
}
