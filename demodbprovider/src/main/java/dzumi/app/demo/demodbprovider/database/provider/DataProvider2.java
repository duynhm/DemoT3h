package dzumi.app.demo.demodbprovider.database.provider;

import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import dzumi.app.demo.demodbprovider.model.Country;

/**
 * Created by Dzumi on 6/3/2016.
 * Dump data
 */
public class DataProvider2 implements IDataProvider {
    Context mContext;

    public DataProvider2(Context mContext) {
        this.mContext = mContext;
    }
    String[] nameEn = new String[]{"Vietnam", "China", "Japan", "Korea"};
    String[] nameVi = new String[]{"Việt Nam", "Trung Quốc", "Nhật Bản", "Hàn Quốc"};
    String[] flag = new String[]{"vietnam.png", "china.png", "japan.png", "korea.png"};
    @Override
    public List<Country> getCountries() {
        List<Country> countries = new ArrayList<>();
        for (int i = 0; i < nameEn.length; i++) {
            countries.add(new Country(i+1,nameEn[i], nameVi[i], flag[i]));
        }
        return countries;
    }

    @Override
    public long insertCountry(Country country) {
        return 0;
    }
}
