package kitkare.kitkare.app.data.remote.services;

import com.squareup.okhttp.Callback;

public class DeviceService extends BaseService {
    public void giveFood(String url, Callback callback) throws Exception {
        super.get(url, callback);
    }
}
