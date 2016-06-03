package dzumi.app.demo.demodbprovider.database.provider;

import java.util.List;

import dzumi.app.demo.demodbprovider.model.Country;

/**
 * Created by Dzumi on 6/3/2016.
 */
public interface IDataProvider {
    //interface này được dùng để khai báo các phương thức sử dụng để truy xuất DB
    //hoặc dump data


    List<Country> getCountries();
    long insertCountry(Country country);

    // TODO: khai báo thêm các phương thức cần thiết khác 
}
