package kitkare.kitkare.app.data.remote.services;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.InputStream;

public class DeviceService extends BaseService {
    public void giveFood(String url, Callback callback) throws Exception {
        super.get(url, callback);
    }

    public void giveWater(String url, Callback callback) throws Exception {
        super.get(url, callback);
    }

    public void turnLightsOn(String url, Callback callback) throws Exception {
        super.get(url, callback);
    }

    public void turnLightsOff(String url, Callback callback) throws Exception {
        super.get(url, callback);
    }

    public void checkIfLightsAreOn(String url, Callback callback) throws Exception {
        super.get(url, callback);
    }

    public void turnCameraOn(String url, Callback callback) throws Exception {
        Request request = new Request.Builder().url("URL string here")
                //.addHeader("X-CSRFToken", csrftoken)
                //.addHeader("Content-Type", "application/json")
                .build();
        Response response = this.client.newCall(request).execute();

        InputStream in = response.body().byteStream();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//        String result, line = reader.readLine();
//        result = line;
//        while((line = reader.readLine()) != null) {
//            result += line;
//        }
//        System.out.println(result);
        response.body().close();
        //super.get(url, callback);
    }

    public void turnCameraOff(String url, Callback callback) throws Exception {
        super.get(url, callback);
    }
}
