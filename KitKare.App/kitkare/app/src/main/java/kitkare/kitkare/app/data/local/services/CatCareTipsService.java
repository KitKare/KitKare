package kitkare.kitkare.app.data.local.services;

import android.content.Context;

import java.sql.SQLException;
import java.util.List;

import kitkare.kitkare.app.data.local.CatCareTipDataSource;
import kitkare.kitkare.app.data.local.models.CatCareTip;

public class CatCareTipsService {
    private static CatCareTipDataSource cateCareTipdb;

    public static List<CatCareTip> getAll(Context context) throws SQLException {
        if (cateCareTipdb == null) {
            cateCareTipdb = new CatCareTipDataSource(context);
            cateCareTipdb.open();
        }

        return cateCareTipdb.getAllCatCareTips();
    }
}
