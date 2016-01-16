package kitkare.kitkare.app.tasks;

import android.content.Context;
import android.os.Handler;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import kitkare.kitkare.app.common.GlobalConstants;

import kitkare.kitkare.app.data.remote.services.CatCareTipsService;
import kitkare.kitkare.app.data.interfaces.IUpdatePageInfo;
import kitkare.kitkare.app.viewModels.CatCareTipViewModel;


public class GetAllCatCareTipsTask {
    private CatCareTipsService service;
    private String getAllResponse;
    private Context context;
    private IUpdatePageInfo page;
    ArrayList<CatCareTipViewModel> list;

    public GetAllCatCareTipsTask(Context context, IUpdatePageInfo page) {
        this.service = new CatCareTipsService();
        this.context = context;
        this.page = page;
        this.list = new ArrayList<>();
    }

    public void execute(){
        Callback callback = new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                getAllResponse = response.body().string();
                getTipsCollection();
                postToMainThread();
            }
        };

        try {
            service.getAll(GlobalConstants.CATCARETIPS, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getTipsCollection() {
        JSONArray jObject;
        try {
            jObject = new JSONArray(getAllResponse);
            for (int i = 0; i < jObject.length(); i++) {
                CatCareTipViewModel modelToAdd;
                modelToAdd = CatCareTipViewModel.FromModel(jObject.getString(i));
                list.add(modelToAdd);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void postToMainThread(){
        Handler mainHandler = new Handler(context.getMainLooper());

        Runnable updatePage = new Runnable() {
            @Override
            public void run() {
                page.updatePageData(list);
            }
        };

        mainHandler.post(updatePage);
    }
}

