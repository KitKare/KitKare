package kitkare.kitkare.app.tasks.device;


import android.content.Context;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import kitkare.kitkare.app.common.GlobalConstants;
import kitkare.kitkare.app.data.remote.services.DeviceService;

public class ToggleLightsTask {
    private DeviceService service;
    private Context context;

    public ToggleLightsTask(Context context) {
        this.service = new DeviceService();
        this.context = context;
    }

    public void execute() {
        Callback callback = new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String apiResponse = response.body().string();
                //TODO - check response
                if (apiResponse.equals(true)){
                    TurnLightsOffTask turnLightsOffTask = new TurnLightsOffTask(context);
                    turnLightsOffTask.execute();
                }else{
                    TurnLightsOnTask turnLightsOnTask = new TurnLightsOnTask(context);
                    turnLightsOnTask.execute();
                }
            }
        };

        try {
            service.checkIfLightsAreOn(GlobalConstants.LIGHTSAREON, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
