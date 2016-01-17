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

public class TurnCameraOnTask {
    private DeviceService service;
    private Context context;

    public TurnCameraOnTask(Context context) {
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
                //TODO
                if(response.isSuccessful()){
                    postToMainThread();
                }
            }
        };

        try {
            service.turnCameraOn(GlobalConstants.TURNCAMERAON, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void postToMainThread(){
        Handler mainHandler = new Handler(context.getMainLooper());

        Runnable updatePage = new Runnable() {
            @Override
            public void run() {
                Helper.makeText(context, "Camera on!");
            }
        };

        mainHandler.post(updatePage);
    }
}
