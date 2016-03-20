package dzumi.app.demo.demoencrypteddatabases.provider;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dzumi.app.demo.demoencrypteddatabases.db.Country;
import dzumi.app.demo.demoencrypteddatabases.db.DBContext;

/**
 * Created by Dzumi on 3/19/2016.
 */
public class DataProvider extends BaseDataProvider implements IDataProvider {

    DBContext mDbContext;
    public DataProvider(Context context){
        super(context);
        mDbContext = new DBContext(context);
    }
    @Override
    public List<Country> get() {
        return fetchAll(mDbContext.getCursor());
    }

    @Override
    public List<Country> get(String nameEn) {
        List<Country> countries = null;
        SQLiteDatabase sqLiteDatabase = mDbContext.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(Country.TABLE_NAME, null,"nameEn=?", new String[]{nameEn}, null, null,null);
        countries = fetchAll(cursor);
        sqLiteDatabase.close();
        return countries;
    }

    @Override
    public long insert(Country country) {
        try {
            return mDbContext.insert(country);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    protected Country fetch(Cursor cursor) {
        String nameEn = new String(cursor.getBlob(cursor.getColumnIndex(Country.COLUMN_NAME_EN)));
        String nameVi = new String(cursor.getBlob(cursor.getColumnIndex(Country.COLUMN_NAME_VI)));
        try {
            return new Country(nameEn, nameVi, true);
        } catch (Exception e) {
            e.printStackTrace();
            return new Country();
        }

    }


}
