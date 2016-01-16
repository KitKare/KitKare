package kitkare.kitkare.app.tasks;

import android.content.Context;
import android.os.AsyncTask;

import java.sql.SQLException;
import java.util.ArrayList;

import kitkare.kitkare.app.common.Helper;
import kitkare.kitkare.app.data.local.CatCareTipDataSource;
import kitkare.kitkare.app.data.local.models.CatCareTip;
import kitkare.kitkare.app.data.local.services.CatCareTipsService;
import kitkare.kitkare.app.viewModels.CatCareTipViewModel;

public class AddToFavoritesTask extends AsyncTask<String, Void, CatCareTip> {
    private Context context;
    private CatCareTipViewModel model;
    CatCareTipsService service;

    public AddToFavoritesTask(Context context, CatCareTipViewModel model) {
        this.context = context;
        this.model = model;
        this.service = new CatCareTipsService();
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
        Helper.makeText(context, "Tip added to favorites.");
    }

    private void saveToDb(CatCareTipViewModel model) throws SQLException {
        service.add(context, model);
    }
}
