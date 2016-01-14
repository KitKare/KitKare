package kitkare.kitkare.app.data.remote.services;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class CatCareTipsService {
    private String getAllResponse;
    private OkHttpClient client = new OkHttpClient();

    public void getAll(String url) throws Exception {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Callback callback = new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                getAllResponse = response.body().string();
            }
        };

        client.newCall(request).enqueue(callback);

    }

    public String getGetAllResponse() {
        return getAllResponse;
    }
}
