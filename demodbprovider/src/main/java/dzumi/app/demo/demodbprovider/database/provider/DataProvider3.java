package dzumi.app.demo.demodbprovider.database.provider;

import java.util.ArrayList;
import java.util.List;

import dzumi.app.demo.demodbprovider.model.Country;

/**
 * Created by Dzumi on 6/3/2016.
 * lấy dữ liệu từ asset DB
 */
public class DataProvider3 implements IDataProvider {
    @Override
    public List<Country> getCountries() {
        // TODO: Viết phương thức xử lý lấy dữ liệu từ file db trong thư mục asset
        return new ArrayList<>();
    }

    @Override
    public long insertCountry(Country country) {
        return 0;
    }
}
