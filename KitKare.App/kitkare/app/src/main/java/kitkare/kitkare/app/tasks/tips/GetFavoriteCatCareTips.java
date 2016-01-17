package kitkare.kitkare.app.tasks.tips;

import android.content.Context;
import android.os.AsyncTask;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kitkare.kitkare.app.data.interfaces.IUpdatePageData;
import kitkare.kitkare.app.data.local.models.CatCareTip;
import kitkare.kitkare.app.data.local.services.CatCareTipsService;
import kitkare.kitkare.app.viewModels.CatCareTipViewModel;

public class GetFavoriteCatCareTips extends AsyncTask<String, Void, ArrayList<CatCareTipViewModel>> {
    private Context context;
    CatCareTipsService service;
    private IUpdatePageData page;

    public GetFavoriteCatCareTips(Context context, IUpdatePageData page){
        this.context = context;
        this.service = new CatCareTipsService();
        this.page = page;
    }
    @Override
    protected ArrayList<CatCareTipViewModel> doInBackground(String... params) {
        try {
            List<CatCareTip> favoriteTips = service.getAll(context);
            ArrayList<CatCareTipViewModel> list = new ArrayList<>();

            for (int i = 0; i < favoriteTips.size(); i++) {
                CatCareTipViewModel modelToAdd;
                modelToAdd = CatCareTipViewModel.FromModel(favoriteTips.get(i));
                list.add(modelToAdd);
            }

            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }
    protected void onPostExecute(ArrayList<CatCareTipViewModel> result) {
        super.onPostExecute(result);
        page.updatePageData(result);
    }
}
