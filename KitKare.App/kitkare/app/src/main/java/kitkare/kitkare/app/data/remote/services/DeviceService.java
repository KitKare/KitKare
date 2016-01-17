package kitkare.kitkare.app.data.remote.services;

import com.squareup.okhttp.Callback;

public class DeviceService extends BaseService {

    public void giveFood(String url, Callback callback) throws Exception {
        super.authGet(url, callback);
    }

    public void giveWater(String url, Callback callback) throws Exception {
        super.authGet(url, callback);
    }

    public void turnLightsOn(String url, Callback callback) throws Exception {
        super.authGet(url, callback);
    }

    public void turnLightsOff(String url, Callback callback) throws Exception {
        super.authGet(url, callback);
    }

    public void checkIfLightsAreOn(String url, Callback callback) throws Exception {
        super.authGet(url, callback);
    }

    public void turnCameraOff(String url, Callback callback) throws Exception {
        super.authGet(url, callback);
    }
}
