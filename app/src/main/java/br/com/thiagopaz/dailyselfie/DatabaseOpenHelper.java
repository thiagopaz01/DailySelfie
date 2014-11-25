package br.com.thiagopaz.dailyselfie;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by thiago.fernandes on 25/11/2014.
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper {
    final static String TABLE_NAME = "selfies";
    final static String PHOTO_PATH = "photo_path";
    final static String PHOTO_DATE = "photo_date";
    final static String _ID = "_id";
    final static String[] columns = { _ID, PHOTO_PATH,PHOTO_DATE };

    final private static String CREATE_CMD =
            "CREATE TABLE selfies (" + _ID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + PHOTO_PATH + " TEXT NOT NULL," + PHOTO_DATE + " DATE NOT NULL)";

    final private static String NAME = "selfies_db";
    final private static Integer VERSION = 1;
    final private Context mContext;

    public DatabaseOpenHelper(Context context) {
        super(context, NAME, null, VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CMD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
