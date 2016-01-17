package kitkare.kitkare.app.tasks.device;


import android.content.Context;
import android.os.Handler;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import kitkare.kitkare.app.common.GlobalConstants;
import kitkare.kitkare.app.common.Helper;
import kitkare.kitkare.app.data.remote.services.DeviceService;

public class TurnLightsOffTask {
    private DeviceService service;
    private Context context;

    public TurnLightsOffTask(Context context) {
        this.service = new DeviceService();
        this.context = context;
    }

    public void execute(){
        Callback callback = new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String message = "";
                if(response.isSuccessful()){
                    message = "Lights off!";
                    postToMainThread(message);
                }else{
                    message = "Uh oh, something went wrong!";
                    postToMainThread(message);
                }
            }
        };

        try {
            service.turnLightsOff(GlobalConstants.TURNLIGHTSOFF, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void postToMainThread(final String message){
        Handler mainHandler = new Handler(context.getMainLooper());

        Runnable updatePage = new Runnable() {
            @Override
            public void run() {
                Helper.makeText(context, message);
            }
        };

        mainHandler.post(updatePage);
    }
}
