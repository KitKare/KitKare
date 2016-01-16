package kitkare.kitkare.app.data.remote.services;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

public class CatCareTipsService {
    private OkHttpClient client;

    public CatCareTipsService(){
        this.client = new OkHttpClient();
    }

    public void getAll(String url, Callback callback) throws Exception {
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(callback);
    }
}