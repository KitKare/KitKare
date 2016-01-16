package kitkare.kitkare.app.data.remote.services;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

public class CatCareTipsService extends BaseService{
    public void getAll(String url, Callback callback) throws Exception {
        super.get(url, callback);
    }
}