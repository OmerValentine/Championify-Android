package net.championify.championify;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ChampionsDBHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION = 2;
    public static final String CHAMPIONS_TABLE = "champions";
    public static final String CHAMPIONS_ID = "_id";
    public static final String CHAMPIONS_NAME = "name";
    public static final String CHAMPIONS_TITLE = "title";
    public static final String CHAMPIONS_PRIMARYROLE = "primaryrole";
    public static final String CHAMPIONS_SECONDARYROLE = "secondaryrole";
    public static final String CHAMPIONS_KEY = "key";
    public static final String CHAMPIONS_ICON = "icon";
    public static final String CHAMPIONS_IMAGE = "image";
    public static final String CHAMPIONS_SKINS = "skins";

    public ChampionsDBHelper(Context context) {
        super(context, "ChampionsDB.db", null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = String.format("CREATE TABLE %1$s (%2$s INTEGER PRIMARY KEY AUTOINCREMENT, %3$s TEXT, %4$s TEXT, %5$s TEXT, %6$s TEXT, %7$s TEXT, %8$s INTEGER, %9$s INTEGER, %10$s INTEGER)",
                CHAMPIONS_TABLE, CHAMPIONS_ID, CHAMPIONS_NAME, CHAMPIONS_TITLE, CHAMPIONS_PRIMARYROLE, CHAMPIONS_SECONDARYROLE, CHAMPIONS_KEY, CHAMPIONS_ICON, CHAMPIONS_IMAGE, CHAMPIONS_SKINS);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CHAMPIONS_TABLE);
        onCreate(db);
    }
}
