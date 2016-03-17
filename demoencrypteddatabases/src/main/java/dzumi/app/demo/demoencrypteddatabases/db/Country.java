package dzumi.app.demo.demoencrypteddatabases.db;

import dzumi.app.demo.demoencrypteddatabases.main.Encryptor;

/**
 * Created by duy on 3/14/2016.
 */
public class Country {
    private final String KEY_ENCRYPT = "demo_encrypt";
    public static final String TABLE_NAME = "Country";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME_EN = "nameEn";
    public static final String COLUMN_NAME_VI = "nameVi";

    private String nameEn;
    private String nameVi;

    private int _id;

    Encryptor mEncryptor;



    public Country(String nameEn, String nameVi, boolean ... isEncrypt) throws Exception {
        mEncryptor = new Encryptor(KEY_ENCRYPT);
        if(isEncrypt!= null){
            this.nameEn = mEncryptor.decrypt(nameEn);
            this.nameVi = mEncryptor.decrypt(nameVi);

        }else
        {
            this.nameEn = nameEn;
            this.nameVi = nameVi;

        }
    }

    public Country() {
        mEncryptor = new Encryptor(KEY_ENCRYPT);
    }


    public String getNameEn() {
        return nameEn;
    }
    public String getNameEnEncrypt() throws Exception {
        return mEncryptor.encrypt(nameEn);
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }
    public void setNameEnDecrypt(String code) throws Exception {
        this.nameEn = mEncryptor.decrypt(code);
    }

    public String getNameVi() {
        return nameVi;
    }
    public String getNameViEncrypt() throws Exception {
        return mEncryptor.encrypt(nameVi);
    }

    public void setNameVi(String nameVi) {
        this.nameVi = nameVi;
    }
    public void setNameViDecrypt(String code) throws Exception {
        this.nameVi = mEncryptor.decrypt(code);
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
