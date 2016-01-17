package kitkare.kitkare.app.tasks.tips;

import android.content.Context;
import android.os.AsyncTask;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kitkare.kitkare.app.common.Helper;
import kitkare.kitkare.app.data.local.CatCareTipDataSource;
import kitkare.kitkare.app.data.local.models.CatCareTip;
import kitkare.kitkare.app.data.local.services.CatCareTipsService;
import kitkare.kitkare.app.viewModels.CatCareTipViewModel;

public class AddToFavoritesTask extends AsyncTask<String, Void, CatCareTip> {
    private Context context;
    private CatCareTipViewModel model;
    private boolean isAlreadyAdded;
    CatCareTipsService service;

    public AddToFavoritesTask(Context context, CatCareTipViewModel model) {
        this.context = context;
        this.model = model;
        this.service = new CatCareTipsService();
        this.isAlreadyAdded = false;
    }

    @Override
    protected CatCareTip doInBackground(String... params) {
        try {
            this.saveToDb(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(CatCareTip result) {
        super.onPostExecute(result);
        if (isAlreadyAdded){
            Helper.makeText(context, "You already have that in your favorites.");
        } else{
            Helper.makeText(context, "Added to favorites.");
        }
    }

    private void saveToDb(CatCareTipViewModel model) throws SQLException {
        List<CatCareTip> all = service.getAll(context);
        for (int i = 0; i < all.size(); i++){
            if (model.getTitle().equals(all.get(i).getTitle())){
                isAlreadyAdded = true;
                return;
            }
        }

        service.add(context, model);
    }
}
