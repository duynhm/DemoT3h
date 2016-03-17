package dzumi.app.demo.demoencrypteddatabases.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    public DBContext(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
       
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        // Yeah, this isn't implemented in production yet either. It's low on the list, but definitely "on the list"

    }

    // Assumes Encryptor was used to convert the fields of name before calling insert
    public void insert(Country country) throws Exception {
        ContentValues cv = new ContentValues();

        cv.put(Country.COLUMN_NAME_EN, country.getNameEnEncrypt().getBytes());
        cv.put(Country.COLUMN_NAME_VI, country.getNameViEncrypt().getBytes());
       
        getWritableDatabase().insert(Country.TABLE_NAME, null, cv);
    }

    // returns the encrypted values to be manipulated with the decryptor.
    public Country get(Integer index) {
       
        SQLiteDatabase db = getReadableDatabase();
        Country country = new Country();
        Cursor cur = null;
        try {
            cur = db.query(Country.TABLE_NAME, null, "_id='"+index.toString() +"'", null, null, null, Country.COLUMN_ID);
            // cursors connsistently return before the first element. Move to the first.
            cur.moveToFirst();
            byte[] nameEn = cur.getBlob(cur.getColumnIndex(Country.COLUMN_NAME_EN));
            byte [] nameVi = cur.getBlob(cur.getColumnIndex(Country.COLUMN_NAME_VI));

            country = new Country(new String(nameEn), new String(nameVi));

            cur.close();
        } catch(Exception e) {
            System.out.println(e.toString());
            // Do nothing - we want to return the empty host name map.
        }
        return country;
       
    }

    // NOTE: This routine assumes "String name" is the encrypted version of the string.   
    public Country getByNameEn(String nameEn) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cur = null;
        String check = null;
        try {
            // Note - the production version of this routine actually uses the "where" field to get the correct
            // element instead of looping the table. This is here for your debugging use.
            cur = db.query(Country.TABLE_NAME, null, null, null, null, null, null);
            for(cur.moveToFirst();(!cur.isLast());cur.moveToNext()) {
                check = new String(cur.getBlob(cur.getColumnIndex(Country.COLUMN_NAME_EN)));
                if(check.equals(nameEn))
                        return new Country(check, new String(cur.getBlob(cur.getColumnIndex(Country.COLUMN_NAME_VI))));
               
            }
            if(cur.isLast())
                return new Country();
       
            return new Country(cur.getString(cur.getColumnIndex(Country.COLUMN_NAME_EN)), cur.getString(cur.getColumnIndex(Country.COLUMN_NAME_VI)));
        } catch(Exception e) {
            System.out.println(e.toString());
            return new Country();
        }
        finally {
            if(cur!= null)
                cur.close();
        }

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