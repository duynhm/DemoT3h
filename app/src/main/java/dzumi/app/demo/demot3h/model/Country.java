package dzumi.app.demo.demot3h.model;

/**
 * Created by Dzumi on 5/6/2016.
 */
public class Country {
    String nameEn;
    String nameVi;

    public Country(String nameEn, String nameVi) {
        this.nameEn = nameEn;
        this.nameVi = nameVi;
    }

    public Country() {
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
}
