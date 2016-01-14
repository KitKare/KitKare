package kitkare.kitkare.app.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
    public static final String TABLE_CATCARETIP = "catcaretip";
    public static final String COLUMN_CATCARETIP_ID = "_id";
    public static final String COLUMN_CATCARETIP_TITLE = "title";
    public static final String COLUMN_CATCARETIP_CONTENT = "content";
    public static final String COLUMN_CATCARETIP_CREATEDON = "createdon";
    public static final String COLUMN_CATCARETIP_SAVEDON = "savedon";

    private static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "kitkare.db";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_CATCARETIP_TABLE =
                "CREATE TABLE " + SQLiteContract.PageEntry.TABLE_NAME + " (" +
                        SQLiteContract.PageEntry._ID + " INTEGER PRIMARY KEY, " +
                        SQLiteContract.PageEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
                        SQLiteContract.PageEntry.COLUMN_CONTENT + " TEXT NOT NULL, " +
                        SQLiteContract.PageEntry.COLUMN_CREATEDON + " DATE NOT NULL, " +
                        SQLiteContract.PageEntry.COLUMN_SAVEDON + " DATE NOT NULL" +
                        ");";

        sqLiteDatabase.execSQL(SQL_CREATE_CATCARETIP_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
