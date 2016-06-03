package dzumi.app.demo.demodbprovider.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dzumi.app.demo.demodbprovider.database.DBContext;
import dzumi.app.demo.demodbprovider.model.Country;

/**
 * Created by Dzumi on 6/3/2016.
 */
public class CountryDAO {
    Context mContext;
    DBContext dbContext;
    public CountryDAO(Context context) {
        this.mContext = context;
        dbContext = new DBContext(context);
    }

    /**
     * Phương thức insert 1 đối tượng country
     * @param country
     * @return id của country vừa insert
     */
    public long insert(Country country){
        SQLiteDatabase database = dbContext.getWritableDatabase();

        long id = database.insert(Country.TABLE_NAME,null, mapContentValues(country));

        database.close();
        return id;
    }

    /**
     * Phương thức insert 1 danh sách đối tượng country
     * @param countries
     * @return số lượng record được insert thành công
     */
    public int insert(List<Country> countries){
        SQLiteDatabase database = dbContext.getWritableDatabase();

        database.beginTransaction();
        int count = 0;
        try {
            for (Country country : countries) {
                database.insert(Country.TABLE_NAME, null, mapContentValues(country));
            }
            database.setTransactionSuccessful();
            count = countries.size();
        }catch (Exception e){

        }
        database.endTransaction();
        database.close();
        return count;
    }

    public int update(Country country){
        SQLiteDatabase database = dbContext.getWritableDatabase();
        int count =  database.update(Country.TABLE_NAME, mapContentValues(country),
                Country.COL_ID + "=?", new String[]{country.get_id() + ""});
        database.close();
        return count;
    }

    public int delete(long id){
        SQLiteDatabase database = dbContext.getWritableDatabase();
        int count =  database.delete(Country.TABLE_NAME,
                Country.COL_ID + "=?", new String[]{id + ""});
        return count;
    }

    public int delete(String where, String[] whereArgs){
        SQLiteDatabase database = dbContext.getWritableDatabase();
        int count =  database.delete(Country.TABLE_NAME,
                where, whereArgs);
        return count;
    }

    public List<Country> get(){
        SQLiteDatabase database = dbContext.getReadableDatabase();
        List<Country> countries = new ArrayList<>();
        Cursor cursor = database.query(Country.TABLE_NAME, null,null,null,null,null,null);
        if(cursor != null){
            if(cursor.moveToFirst()){
                do{
                    Country country = new Country();
                    country.set_id(cursor.getLong(cursor.getColumnIndex(Country.COL_ID)));
                    country.setNameEn(cursor.getString(cursor.getColumnIndex(Country.COL_NAME_EN)));
                    country.setNameVi(cursor.getString(cursor.getColumnIndex(Country.COL_NAME_VI)));
                    country.setFlag(cursor.getString(cursor.getColumnIndex(Country.COL_FLAG)));

                    countries.add(country);
                }while (cursor.moveToNext());
            }
            cursor.close();
        }
        database.close();
        return countries;
    }
    //Cần lấy danh sách quốc gia có dân số lớn hơn n người
    public List<Country> get(int n){
        SQLiteDatabase database = dbContext.getReadableDatabase();

        Cursor cursor = database.query(Country.TABLE_NAME,
                null,"dan so > n",null,null,null, Country.COL_NAME_EN + " ASC");

        List<Country> countries = fetchAll(cursor);
        database.close();
        return countries;
    }

    //tự viết
    public Country get(long id){
        return null;
    }

    private ContentValues mapContentValues(Country country){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Country.COL_NAME_EN, country.getNameEn());
        contentValues.put(Country.COL_NAME_VI, country.getNameVi());
        contentValues.put(Country.COL_FLAG, country.getFlag());

        return contentValues;
    }

    private Country fetch(Cursor cursor){
        Country country = new Country();
        country.set_id(cursor.getLong(cursor.getColumnIndex(Country.COL_ID)));
        country.setNameEn(cursor.getString(cursor.getColumnIndex(Country.COL_NAME_EN)));
        country.setNameVi(cursor.getString(cursor.getColumnIndex(Country.COL_NAME_VI)));
        country.setFlag(cursor.getString(cursor.getColumnIndex(Country.COL_FLAG)));
        return country;
    }

    private List<Country> fetchAll(Cursor cursor){
        List<Country> countries = new ArrayList<>();
        if(cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    countries.add(fetch(cursor));
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return countries;
    }
}
