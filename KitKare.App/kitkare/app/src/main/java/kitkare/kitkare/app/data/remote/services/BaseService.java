package kitkare.kitkare.app.data.remote.services;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

public abstract class BaseService {
    protected OkHttpClient client;

    protected BaseService(){
        this.client = new OkHttpClient();
    }

    public void get(String url, Callback callback) throws Exception {
        Request request = new Request.Builder()
                .url(url)
                .build();

        this.client.newCall(request).enqueue(callback);
    }
}
