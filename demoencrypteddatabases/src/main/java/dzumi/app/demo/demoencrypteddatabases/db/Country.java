package dzumi.app.demo.demoencrypteddatabases.db;

import dzumi.app.demo.demoencrypteddatabases.main.CryptoUtils;
import dzumi.app.demo.demoencrypteddatabases.main.Encryptor;

/**
 * Created by duy on 3/14/2016.
 */
public class Country {
    public static final String KEY_ENCRYPT = "demo_encrypt_dzm";
    public static final String TABLE_NAME = "Country";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME_EN = "nameEn";
    public static final String COLUMN_NAME_VI = "nameVi";

    private String nameEn;
    private String nameVi;

    private int _id;

    public Country() {
    }

    public Country(String nameEn, String nameVi, boolean ... isEncrypt) throws Exception {
        if(isEncrypt!= null && isEncrypt.length > 0){
            this.nameEn = CryptoUtils.decrypt(KEY_ENCRYPT, nameEn);
            this.nameVi = CryptoUtils.decrypt(KEY_ENCRYPT, nameVi);

        }else
        {
            this.nameEn = nameEn;
            this.nameVi = nameVi;

        }
    }


    public String getNameEn() {
        return nameEn;
    }
    public String getNameEnEncrypt() throws Exception {
        return CryptoUtils.encrypt(KEY_ENCRYPT, nameEn);
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }
    public void setNameEnDecrypt(String code) throws Exception {
        this.nameEn = CryptoUtils.decrypt(KEY_ENCRYPT, code);
    }

    public String getNameVi() {
        return nameVi;
    }
    public String getNameViEncrypt() throws Exception {
        return CryptoUtils.encrypt(KEY_ENCRYPT,nameVi);
    }

    public void setNameVi(String nameVi) {
        this.nameVi = nameVi;
    }
    public void setNameViDecrypt(String code) throws Exception {
        this.nameVi = CryptoUtils.decrypt(KEY_ENCRYPT,code);
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
