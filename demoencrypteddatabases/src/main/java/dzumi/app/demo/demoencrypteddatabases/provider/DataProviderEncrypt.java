package dzumi.app.demo.demoencrypteddatabases.provider;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import net.sqlcipher.database.SQLiteDatabase;

import java.util.List;

import dzumi.app.demo.demoencrypteddatabases.db.Country;
import dzumi.app.demo.demoencrypteddatabases.db.DBContextEncrypt;

/**
 * Created by Dzumi on 3/19/2016.
 */
public class DataProviderEncrypt extends BaseDataProvider implements IDataProvider{

    DBContextEncrypt mDbContextEncrypt;
    public DataProviderEncrypt(Context context) {
        super(context);
        mDbContextEncrypt = new DBContextEncrypt(context);
    }

    @Override
    public List<Country> get() {
        List<Country> countries = null;
        SQLiteDatabase sqLiteDatabase = mDbContextEncrypt.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(Country.TABLE_NAME, null,null,null, null, null,null);
        countries = fetchAll(cursor);
        sqLiteDatabase.close();
        return countries;
    }

    @Override
    public List<Country> get(String nameEn) {
        List<Country> countries = null;
        SQLiteDatabase sqLiteDatabase = mDbContextEncrypt.getReadableDatabase();
        String selector = "nameEn like \'%"+nameEn+"%\'";
        Log.d("database", selector);
//        Cursor cursor = sqLiteDatabase.query(Country.TABLE_NAME, null,"nameEn like '?'", new String[]{"%"+nameEn+"%"}, null, null,null);
        Cursor cursor = sqLiteDatabase.query(Country.TABLE_NAME, null,selector, null, null, null,null);
        countries = fetchAll(cursor);
        sqLiteDatabase.close();
        return countries;
    }

    @Override
    public long insert(Country country) {
        SQLiteDatabase sqLiteDatabase = mDbContextEncrypt.getWritableDatabase();
        long id = sqLiteDatabase.insert(Country.TABLE_NAME, null, setContentValues(country));
        sqLiteDatabase.close();
        return id;

    }
}
