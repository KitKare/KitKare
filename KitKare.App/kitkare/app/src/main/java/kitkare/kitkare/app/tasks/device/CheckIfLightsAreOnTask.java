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
import java.util.ArrayList;

import kitkare.kitkare.app.activities.DashboardActivity;
import kitkare.kitkare.app.common.GlobalConstants;
import kitkare.kitkare.app.common.Helper;
import kitkare.kitkare.app.data.interfaces.IUpdatePageData;
import kitkare.kitkare.app.data.remote.services.DeviceService;
import kitkare.kitkare.app.viewModels.CatCareTipViewModel;

public class CheckIfLightsAreOnTask {
    private DeviceService service;
    private Context context;
    private IUpdatePageData page;
    ArrayList<String> list;

    public CheckIfLightsAreOnTask(Context context, IUpdatePageData page) {
        this.service = new DeviceService();
        this.context = context;
        this.page = page;
        list = new ArrayList<>();
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
                Boolean areLightson = null;
                try {
                    JSONObject jObject = new JSONObject(apiResponse);
                    areLightson = jObject.getBoolean("LightsAreOn");
                    list.add(areLightson.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                postToMainThread();
            }
        };

        try {
            service.checkIfLightsAreOn(GlobalConstants.LIGHTSAREON, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void postToMainThread(){
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
