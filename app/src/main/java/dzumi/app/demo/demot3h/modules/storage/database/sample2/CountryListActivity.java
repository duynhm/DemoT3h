package dzumi.app.demo.demot3h.modules.storage.database.sample2;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dzumi.app.demo.demot3h.R;
import dzumi.app.demo.demot3h.modules.storage.database.Country;
import dzumi.app.demo.demot3h.modules.storage.database.CountryAdapter;

public class CountryListActivity extends AppCompatActivity implements View.OnClickListener{
    ListView listView;
    Button btnLoadDB;
    Button btnInsert;
    Button btnDelete;
    Button btnUpdate;
    String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_list_sample_2);
        listView = (ListView) findViewById(R.id.listView);
        btnLoadDB = (Button) findViewById(R.id.btnLoadDB);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnInsert = (Button) findViewById(R.id.btnInsert);

        btnLoadDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDB();
                setAdapter(getData());
            }
        });
        btnDelete.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnInsert.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.btnDelete){
//            Toast.makeText(CountryListActivity.this, "Delete", Toast.LENGTH_SHORT).show();
//            delALl();
            delete();
        }else if(id == R.id.btnInsert){
//            Toast.makeText(CountryListActivity.this, "Insert", Toast.LENGTH_SHORT).show();
            insert();
        }else if(id == R.id.btnUpdate){
//            Toast.makeText(CountryListActivity.this, "Update", Toast.LENGTH_SHORT).show();
            update();
        }

        //refesh ui
        setAdapter(getData());
    }
    void initDB(){
        //buoc 1: copy file db vao trong bo nho trong

        //kiem tra file db da ton tai trong internal chua,
        //da ton tai --> bo qua
        //chua ton tai --> copy
        path = getFilesDir().getAbsolutePath() + "/DBCountry";
        File file = new File(path);
        if (!file.exists()) {
            AssetManager assetManager = getAssets();
            try {
                BufferedInputStream bis = new BufferedInputStream(assetManager.open("DBCountry"));
                FileOutputStream fos = openFileOutput("DBCountry", Context.MODE_PRIVATE);

                byte[] buffer = new byte[512];
                int i = 0;
                while ((i = bis.read(buffer)) != -1) {
                    fos.write(buffer, 0, i);
                }

                bis.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    List<Country> getData(){
        //buoc 2: mo file db
        SQLiteDatabase sqLiteDatabase = openDB();

        //buoc 3: truy van du lieu tu table country
        Cursor cursor = sqLiteDatabase.query(Country.TABLE_NAME, null, null, null, null, null,
                Country.COL_NAME_EN + " ASC");
        //trong qua trinh truy van co kha nag bi loi hoac trong truy van dc --> null
//        List<Country> countries = null;
        List<Country> countries =  new ArrayList<>();;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                //chac chan la co du lieu --> lay du lieu
//                countries = new ArrayList<>();
                do {
                    Country country = new Country();
                    country.set_id(cursor.getInt(cursor.getColumnIndex(Country.COL_ID)));
                    country.setNameEn(cursor.getString(cursor.getColumnIndex(Country.COL_NAME_EN)));
                    country.setNameVi(cursor.getString(cursor.getColumnIndex(Country.COL_NAME_VI)));
                    country.setFlag(cursor.getString(cursor.getColumnIndex(Country.COL_FLAG)));

                    countries.add(country);
                }
                while (cursor.moveToNext());
            }
            cursor.close();
            sqLiteDatabase.close();
        }
        return countries;
    }
    void setAdapter(List<Country> countries){
        //buoc 4: load ds countries len listview
        CountryAdapter countryAdapter = new CountryAdapter(CountryListActivity.this,
                R.layout.item_list_view_countries, countries);
        listView.setAdapter(countryAdapter);
    }

    //open DB
    SQLiteDatabase openDB(){
        return SQLiteDatabase.openDatabase(path,
                null, SQLiteDatabase.OPEN_READWRITE);
    }

    ContentValues getContentValues(Country country){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Country.COL_NAME_EN, country.getNameEn());
        contentValues.put(Country.COL_NAME_VI, country.getNameVi());
        contentValues.put(Country.COL_FLAG, country.getFlag());
        return contentValues;
    }

    void insert(){
        //buoc 1: mo db
        SQLiteDatabase database = openDB();

        //buoc 2: insert
        Country country = new Country();
        long temp = System.currentTimeMillis();
        country.setNameEn("nameEN - " + temp);
        country.setNameVi("nameVI - " + temp);
        country.setFlag("flag" + temp + ".png");

        //insert tra ve gia tri ID
         database.insert(Country.TABLE_NAME,null, getContentValues(country));
         nameENLastest = country.getNameEn();
        //buoc 3: close DB
        database.close();
    }
//    long lastestID;
    String nameENLastest;
    void update(){
        //buoc 1: mo db
        SQLiteDatabase database = openDB();

        //buoc 2: update
        Country country = new Country();
        long temp = System.currentTimeMillis();
        country.setNameEn("nameEN - " + temp);
        country.setNameVi("nameVI - " + temp);
        country.setFlag("flag" + temp + ".png");

        /*int count = database.update(Country.TABLE_NAME, getContentValues(country),
                Country.COL_ID + " = ?", new String[]{lastestID+""} );*/
        int count = database.update(Country.TABLE_NAME, getContentValues(country),
                Country.COL_NAME_EN + " = '"+nameENLastest+"'",null);
        if(count> 0)
            Toast.makeText(CountryListActivity.this, "update success", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(CountryListActivity.this, "update fail", Toast.LENGTH_SHORT).show();
        //buoc 3: close DB
        database.close();
    }

    void delete(){
        //buoc 1: mo db
        SQLiteDatabase database = openDB();

        //buoc 2: del
       /* int count = database.delete(Country.TABLE_NAME,
                Country.COL_ID + " = ?",
                new String[]{lastestID+""} );*/
        /*int count = database.delete(Country.TABLE_NAME,
                Country.COL_ID + " = " + lastestID,
                null );*/
        int count = database.delete(Country.TABLE_NAME,
                Country.COL_NAME_EN + " = '" + nameENLastest + "'",
                null );
        if(count> 0)
            Toast.makeText(CountryListActivity.this, "del success", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(CountryListActivity.this, "del fail", Toast.LENGTH_SHORT).show();


        //buoc 3: close DB
        database.close();
    }

    void delALl(){
        SQLiteDatabase database = openDB();
        int count = database.delete(Country.TABLE_NAME, "1 = 1", null);
        if(count> 0)
            Toast.makeText(CountryListActivity.this, "del success", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(CountryListActivity.this, "del fail", Toast.LENGTH_SHORT).show();


        //buoc 3: close DB
        database.close();
    }
}
