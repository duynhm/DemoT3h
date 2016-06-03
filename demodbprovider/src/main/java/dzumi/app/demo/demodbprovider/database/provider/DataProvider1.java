package dzumi.app.demo.demodbprovider.database.provider;

import android.content.Context;

import java.util.List;

import dzumi.app.demo.demodbprovider.database.dao.CountryDAO;
import dzumi.app.demo.demodbprovider.model.Country;

/**
 * Created by Dzumi on 6/3/2016.
 * access db
 */
public class DataProvider1 implements IDataProvider {
    Context mContext;
    CountryDAO mCountryDAO;
    public DataProvider1(Context mContext) {
        this.mContext = mContext;
        mCountryDAO = new CountryDAO(mContext);
    }
    @Override
    public List<Country> getCountries() {
        return mCountryDAO.get();
    }

    @Override
    public long insertCountry(Country country) {
        return mCountryDAO.insert(country);
    }
}
