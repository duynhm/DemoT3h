package dzumi.app.demo.demoencrypteddatabases.provider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import dzumi.app.demo.demoencrypteddatabases.db.Country;

/**
 * Created by Dzumi on 3/19/2016.
 */
public class BaseDataProvider {

    protected Context mContext;

    BaseDataProvider(Context context) {
        mContext = context;
    }

    protected Country fetch(Cursor cursor) {
        Country country = new Country();
        country.setNameEn(cursor.getString(cursor.getColumnIndex(Country.COLUMN_NAME_EN)));
        country.setNameVi(cursor.getString(cursor.getColumnIndex(Country.COLUMN_NAME_VI)));
        return country;
    }

    protected List<Country> fetchAll(Cursor cursor) {
        List<Country> countries = new ArrayList<>();
        try {

            if (cursor.moveToFirst()) {
                do {
                    countries.add(fetch(cursor));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (NullPointerException e) {
            return countries;
        }
        return countries;
    }

    protected ContentValues setContentValues(Country country) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Country.COLUMN_NAME_EN, country.getNameEn());
        contentValues.put(Country.COLUMN_NAME_VI, country.getNameVi());
        return contentValues;
    }
}
