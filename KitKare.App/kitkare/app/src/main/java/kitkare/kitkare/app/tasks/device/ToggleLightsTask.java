package kitkare.kitkare.app.tasks.device;


import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import kitkare.kitkare.app.activities.DashboardActivity;
import kitkare.kitkare.app.common.GlobalConstants;
import kitkare.kitkare.app.common.Helper;
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
                Boolean lightsAreOn = null;
                try {
                    JSONObject jObject = new JSONObject(apiResponse);
                    lightsAreOn = jObject.getBoolean("LightsAreOn");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (lightsAreOn == null){
                    String message = "Uh oh, something went wrong!";
                    postToMainThread(message);
                    return;
                }

                if (lightsAreOn) {
                    TurnLightsOffTask turnLightsOffTask = new TurnLightsOffTask(context);
                    turnLightsOffTask.execute();

                } else {
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

    private void postToMainThread(final String message){
        Handler mainHandler = new Handler(context.getMainLooper());

        Runnable backToDashboard = new Runnable() {
            @Override
            public void run() {
                Helper.makeText(context, message);
            }
        };

        mainHandler.post(backToDashboard);
    }
}
