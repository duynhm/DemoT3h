package dzumi.app.demo.demodbprovider.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import dzumi.app.demo.demodbprovider.model.Country;

/**
 * Created by Dzumi on 6/3/2016.
 */
public class DBContext extends SQLiteOpenHelper {
    public final static String DB_NAME = "Country.db";
    public final static int DB_VERSION = 1;

    //phương thức dùng để tạo ra DB nếu chưa tồn tại.
    public DBContext(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

    }

    //phuong thuc nay dung de tao ra table va chi dc goi 1 lan duy nhat
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Country.CREATE_TABLE);
        // TODO: Tạo thêm các table nếu có

    }

    //phương thức này dùng để tái cấu trúc trong db
    //được gọi mỗi khi DB_VERSION thay đổi
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO: Khi version từ 2 trở đi, cần xử lý tại đây
    }
}
