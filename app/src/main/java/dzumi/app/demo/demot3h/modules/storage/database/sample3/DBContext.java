package dzumi.app.demo.demot3h.modules.storage.database.sample3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import dzumi.app.demo.demot3h.modules.storage.database.Country;

/**
 * Created by Dzumi on 5/30/2016.
 */
public class DBContext extends SQLiteOpenHelper {
    final static String DB_NAME = "Country.db";
    final static int DB_VERSION = 1;
    public DBContext(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        //phuong thuc nay dc goi --> lan dau tien dc goi --> tao ra DB
        //kiem tra DB_NAME da ton tai chua
        //chua ton tai --> create DB va ngc lai
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //duoc goi 1 lan duy nhat khi tao db
        //tao table
        db.execSQL(Country.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //duoc goi nhieu lan khi nang version_DB & da ton tai DB trong may
        //alter table

        //van de dat ra o day --> ko quan tam den du lieu hien co
        //chi quan tam cau truc table
        //--> xoa toan bo table --> tao lai table moi
        db.execSQL(Country.DROP_TABLE);

        onCreate(db);
    }
}
