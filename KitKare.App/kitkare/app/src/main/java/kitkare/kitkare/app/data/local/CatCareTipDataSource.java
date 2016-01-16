package kitkare.kitkare.app.data.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kitkare.kitkare.app.common.Helper;
import kitkare.kitkare.app.data.local.models.CatCareTip;

public class CatCareTipDataSource {
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;
    private String[] allColumns = { SQLiteHelper.COLUMN_CATCARETIP_ID,
            SQLiteHelper.COLUMN_CATCARETIP_TITLE, SQLiteHelper.COLUMN_CATCARETIP_CONTENT,
            SQLiteHelper.COLUMN_CATCARETIP_CREATEDON, SQLiteHelper.COLUMN_CATCARETIP_SAVEDON };

    public CatCareTipDataSource(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public CatCareTip createCatCareTip(String title, String content, Date createdon) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.COLUMN_CATCARETIP_TITLE, title);
        values.put(SQLiteHelper.COLUMN_CATCARETIP_CONTENT, content);
        values.put(SQLiteHelper.COLUMN_CATCARETIP_CREATEDON, createdon.toString());
        values.put(SQLiteHelper.COLUMN_CATCARETIP_SAVEDON, (new Date().toString()));

        long insertId = database.insert(SQLiteHelper.TABLE_CATCARETIP, null,
                values);
        Cursor cursor = database.query(SQLiteHelper.TABLE_CATCARETIP,
                allColumns, SQLiteHelper.COLUMN_CATCARETIP_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        CatCareTip newCatCareTip = cursorToCatCareTip(cursor);

        cursor.close();
        return newCatCareTip;
    }

    public void deleteCatCareTip(CatCareTip catCareTip) {
        long id = catCareTip.getId();
        database.delete(SQLiteHelper.TABLE_CATCARETIP, SQLiteHelper.COLUMN_CATCARETIP_ID
                + " = " + id, null);
    }

    public List<CatCareTip> getAllCatCareTips() {
        List<CatCareTip> allTips = new ArrayList<CatCareTip>();

        Cursor cursor = database.query(SQLiteHelper.TABLE_CATCARETIP,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            CatCareTip catCareTip = cursorToCatCareTip(cursor);
            allTips.add(catCareTip);
            cursor.moveToNext();
        }

        cursor.close();
        return allTips;
    }

    private CatCareTip cursorToCatCareTip(Cursor cursor) {
        CatCareTip catCareTip = new CatCareTip();
        catCareTip.setId(cursor.getLong(0));
        catCareTip.setTitle(cursor.getString(1));
        catCareTip.setContent(cursor.getString(2));
        try {
            catCareTip.setCreatedon(Helper.getDayDateFormatter().parse(cursor.getString(3)));
            catCareTip.setModifiedon(Helper.getDayDateFormatter().parse(cursor.getString(4)));
        } catch (ParseException e) {
            e.printStackTrace();
            catCareTip.setCreatedon(new Date());
            catCareTip.setModifiedon(new Date());
        }

        return catCareTip;
    }
}