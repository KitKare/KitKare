package kitkare.kitkare.app.tasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import kitkare.kitkare.app.activities.fragments.dashboard.CatCareTipsFragment;
import kitkare.kitkare.app.common.GlobalConstants;

import kitkare.kitkare.app.data.remote.services.CatCareTipsService;
import kitkare.kitkare.app.viewModels.CatCareTipViewModel;
import kitkare.kitkare.app.viewModels.LoginViewModel;


public class GetAllCatCareTipsTask extends AsyncTask<String, Void, ArrayList<CatCareTipViewModel>> {
    private CatCareTipsFragment fragment;
    private CatCareTipsService service;
    private LoginViewModel user;

    public GetAllCatCareTipsTask(CatCareTipsFragment context, CatCareTipsService service) {
        this.fragment = context;
        this.service = service;
    }

    @Override
    protected ArrayList<CatCareTipViewModel> doInBackground(String... params) {
        String result = "";
        try {
            service.getAll(GlobalConstants.CATCARETIPS);
            result = service.getGetAllResponse();
            JSONArray jObject = null;
            jObject = new JSONArray(result);
            ArrayList<CatCareTipViewModel> list = new ArrayList<CatCareTipViewModel>();
            for(int i=0; i< jObject.length();i++)
            {
                CatCareTipViewModel modelToAdd = null;
                modelToAdd = CatCareTipViewModel.FromModel(jObject.getString(i));
                list.add(modelToAdd);
            }

            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<CatCareTipViewModel>();
    }

    @Override
    protected void onPostExecute(ArrayList<CatCareTipViewModel> result) {
        super.onPostExecute(result);
        this.fragment.updatePageInfo(result);
    }
}

