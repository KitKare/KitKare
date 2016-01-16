package kitkare.kitkare.app.data.local.services;

import android.content.Context;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kitkare.kitkare.app.data.local.CatCareTipDataSource;
import kitkare.kitkare.app.data.local.models.CatCareTip;
import kitkare.kitkare.app.viewModels.CatCareTipViewModel;

public class CatCareTipsService {
    private static CatCareTipDataSource cateCareTipdb;

    public static List<CatCareTip> getAll(Context context) throws SQLException {
        if (cateCareTipdb == null) {
            cateCareTipdb = new CatCareTipDataSource(context);
            cateCareTipdb.open();
        }

        return cateCareTipdb.getAllCatCareTips();
    }

    public static void add(Context context, CatCareTipViewModel model) throws SQLException{
        if (cateCareTipdb == null) {
            cateCareTipdb = new CatCareTipDataSource(context);
            cateCareTipdb.open();
        }

        cateCareTipdb.createCatCareTip(model.getTitle(), model.getContent(), model.getCreatedon());
    }
}
