package dzumi.app.demo.demoencrypteddatabases.db;

import android.content.Context;
import android.util.Log;

import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;

/**
 * Created by Dzumi on 3/19/2016.
 */
public class DBContextEncrypt extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "CountryEncrypt.db";
    public static final int DATABASE_VERSION = 1;

    // SQL statement to create the DB
    private static final String DATABASE_CREATE = "create table "
            + Country.TABLE_NAME + "(" + Country.COLUMN_ID
            + " integer primary key autoincrement, " + Country.COLUMN_NAME_EN
            + " TEXT not null, " + Country.COLUMN_NAME_VI +" TEXT not null);";

    public DBContextEncrypt(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase.loadLibs(context);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        Log.d("database", "onCreate - DBContextEncrypt");

        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.d("database", "onUpgrade - DBContextEncrypt");
    }

    public SQLiteDatabase getReadableDatabase() {
        return (super.getReadableDatabase(Country.KEY_ENCRYPT));
    }

    public SQLiteDatabase getWritableDatabase() {
        return (super.getWritableDatabase(Country.KEY_ENCRYPT));
    }
}
