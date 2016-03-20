package dzumi.app.demo.demoencrypteddatabases.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import dzumi.app.demo.demoencrypteddatabases.db.Country;
import dzumi.app.demo.demoencrypteddatabases.main.Encryptor;

public class DBContext extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Country.db";
    public static final int DATABASE_VERSION = 1;

    // SQL statement to create the DB
    private static final String DATABASE_CREATE = "create table "
            + Country.TABLE_NAME + "(" + Country.COLUMN_ID
            + " integer primary key autoincrement, " + Country.COLUMN_NAME_EN
            + " BLOB not null, " + Country.COLUMN_NAME_VI +" BLOB not null);";

    public DBContext(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
       
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("database", "onCreate - DBContext");
        db.execSQL(DATABASE_CREATE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        // Yeah, this isn't implemented in production yet either. It's low on the list, but definitely "on the list"
        Log.d("database", "onUpgrade - DBContext");

    }

    // Assumes Encryptor was used to convert the fields of name before calling insert
    public long insert(Country country) throws Exception {
        ContentValues cv = new ContentValues();

        cv.put(Country.COLUMN_NAME_EN, country.getNameEnEncrypt().getBytes());
        cv.put(Country.COLUMN_NAME_VI, country.getNameViEncrypt().getBytes());
       
        return getWritableDatabase().insert(Country.TABLE_NAME, null, cv);
    }

    public SQLiteDatabase getReadableDatabase(){
        return getReadableDatabase();
    }

    public SQLiteDatabase getWritableDatabase(){
        return getWritableDatabase();
    }

    // used by our list adapter - coming next in the blog.
    public Cursor getCursor() {
        try {

            return getReadableDatabase().query(Country.TABLE_NAME, null, null, null, null, null, null);
        } catch(Exception e) {
            System.out.println(e.toString());
            return null;
        }
        }
   
    // This is used in our list adapter for mapping to fields.
    public String[] listColumns() {
        return new String[] {Country.COLUMN_NAME_EN};
    }
   
   
}