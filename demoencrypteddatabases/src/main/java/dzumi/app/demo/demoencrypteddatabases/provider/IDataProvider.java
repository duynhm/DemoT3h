package dzumi.app.demo.demoencrypteddatabases.provider;

import java.util.List;

import dzumi.app.demo.demoencrypteddatabases.db.Country;

/**
 * Created by Dzumi on 3/19/2016.
 */
public interface IDataProvider {
    List<Country> get();
    List<Country> get(String nameEn);

    long insert(Country country);
}
