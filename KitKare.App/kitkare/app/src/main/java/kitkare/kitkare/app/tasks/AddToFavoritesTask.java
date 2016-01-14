package kitkare.kitkare.app.tasks;

import android.content.Context;
import android.os.AsyncTask;

import java.sql.SQLException;

import kitkare.kitkare.app.data.local.CatCareTipDataSource;
import kitkare.kitkare.app.data.local.models.CatCareTip;
import kitkare.kitkare.app.viewModels.CatCareTipViewModel;

public class AddToFavoritesTask extends AsyncTask<String, Void, CatCareTip> {
    private Context context;
    private CatCareTipViewModel model;

    public AddToFavoritesTask(Context context, CatCareTipViewModel model) {
        this.context = context;
        this.model = model;
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

    private void saveToDb(CatCareTipViewModel model) throws SQLException {
        CatCareTipDataSource catCareDb = new CatCareTipDataSource(context);
        catCareDb.open();
        catCareDb.createCatCareTip(model.getTitle(), model.getContent(), model.getCreatedon());
        catCareDb.close();
    }
}
