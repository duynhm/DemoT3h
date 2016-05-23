package dzumi.app.demo.demot3h.modules.storage.database;

/**
 * Created by Dzumi on 5/23/2016.
 */
public class Country {
    public final static String TABLE_NAME = "Country";
    public final static String COL_ID = "_id";
    public final static String COL_NAME_EN = "nameEn";
    public final static String COL_NAME_VI = "nameVi";
    public final static String COL_FLAG = "flag";

    int _id;
    String nameEn;
    String nameVi;
    String flag;

    public Country() {
    }

    public Country(int _id, String nameEn, String nameVi, String flag) {
        this._id = _id;
        this.nameEn = nameEn;
        this.nameVi = nameVi;
        this.flag = flag;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameVi() {
        return nameVi;
    }

    public void setNameVi(String nameVi) {
        this.nameVi = nameVi;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
