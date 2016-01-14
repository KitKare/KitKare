package kitkare.kitkare.app.data.local;

import android.provider.BaseColumns;

public class SQLiteContract {

    public static final class PageEntry implements BaseColumns {
        public static final String TABLE_NAME = "catcaretip";

        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_CONTENT = "content";
        public static final String COLUMN_CREATEDON = "createdon";
        public static final String COLUMN_SAVEDON = "savedon";
    }
}
