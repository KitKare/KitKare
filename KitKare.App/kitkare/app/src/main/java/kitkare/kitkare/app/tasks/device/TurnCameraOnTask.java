package kitkare.kitkare.app.tasks.device;


import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import kitkare.kitkare.app.activities.DashboardActivity;
import kitkare.kitkare.app.common.GlobalConstants;
import kitkare.kitkare.app.common.Helper;
import kitkare.kitkare.app.data.interfaces.IUpdatePageData;
import kitkare.kitkare.app.data.remote.services.DeviceService;

public class TurnCameraOnTask {
    private DeviceService service;
    private Context context;
    private Object getAllResponse;
    ArrayList<Object> list;
    private IUpdatePageData page;

    public TurnCameraOnTask(Context context, IUpdatePageData page) {
        this.service = new DeviceService();
        this.context = context;
        this.page = page;
        list = new ArrayList<>();
    }

    public void execute(){
        Callback callback = new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                getAllResponse = response.body();
                list.add(getAllResponse);
                String message = "";
                if(response.isSuccessful()){
                    message = "Camera off!";
                    postToMainThread(message);
                }else{
                    message = "Uh oh, something went wrong!";
                    postToMainThread(message);
                }
            }
        };

        try {
            service.turnCameraOff(GlobalConstants.TURNCAMERAON, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void postToMainThread(final String message){
        Handler mainHandler = new Handler(context.getMainLooper());

        Runnable backToDashboard = new Runnable() {
            @Override
            public void run() {
                page.updatePageData(list);
            }
        };

        mainHandler.post(backToDashboard);
    }
}
