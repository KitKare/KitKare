package kitkare.kitkare.app.tasks;


import android.content.Context;
import android.os.Handler;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import kitkare.kitkare.app.common.GlobalConstants;
import kitkare.kitkare.app.common.Helper;
import kitkare.kitkare.app.data.remote.services.DeviceService;

public class GiveFoodTask {
    private DeviceService service;
    private Context context;

    public GiveFoodTask(Context context) {
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
                if(response.isSuccessful()){
                    postToMainThread();
                }
            }
        };

        try {
            service.giveFood(GlobalConstants.GIVEFOOD, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void postToMainThread(){
        Handler mainHandler = new Handler(context.getMainLooper());

        Runnable updatePage = new Runnable() {
            @Override
            public void run() {
                Helper.makeText(context, "Feeding successful!");
            }
        };

        mainHandler.post(updatePage);
    }
}
