package dzumi.android.demo.personallibrarymanager.content_provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

public class DbContext extends SQLiteOpenHelper {
    private static final String TAG = "database";
//    public static final String DATABASE_NAME = "plmDB.db";
    public static final String DATABASE_NAME = Environment.getExternalStorageDirectory() + "/plmDB.db";
    public static final int DATABASE_VERSION = 1;

    private Context mContext;

    public DbContext(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //To change body of implemented methods use File | Settings | File Templates.
        Log.d(TAG, "onCreate sqlite:");
        db.execSQL(String.format("create table %s ( %s integer primary key, %s integer, %s text)",
                PLMContract.Genres.TABLE_NAME, PLMContract.Genres._ID, PLMContract.Genres.GENRES_ID,
                        PLMContract.Genres.GENRES_NAME));

        /*db.execSQL("create table ? ( ? integer primary key, ? integer, ? text, ? text, ? text, ? text, ? text, ? integer, ? integer )",
                new String[]{PLMContract.Book.TABLE_NAME, PLMContract.Genres._ID, PLMContract.Book.ISBN_BOOK,
                        PLMContract.Book.NAME, PLMContract.Book.AUTHOR, PLMContract.Book.PUBLISHER,
                        PLMContract.Book.DIMENSIONS, PLMContract.Book.LANGUAGE, PLMContract.Book.PAPER_BACK,
                        PLMContract.Book.GENRES_RAW_ID});*/
        db.execSQL(String.format("create table %s ( %s integer primary key, %s integer, %s text, %s text, %s text, %s text, %s text, %s integer, %s integer )",
                PLMContract.Book.TABLE_NAME, PLMContract.Genres._ID, PLMContract.Book.ISBN_BOOK,
                        PLMContract.Book.NAME, PLMContract.Book.AUTHOR, PLMContract.Book.PUBLISHER,
                        PLMContract.Book.DIMENSIONS, PLMContract.Book.LANGUAGE, PLMContract.Book.PAPER_BACK,
                        PLMContract.Book.GENRES_RAW_ID));


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade sqlite:" + newVersion + "/" + oldVersion);
    }

}

