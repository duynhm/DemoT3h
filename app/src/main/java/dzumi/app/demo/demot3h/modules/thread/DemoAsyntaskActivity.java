package dzumi.app.demo.demot3h.modules.thread;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dzumi.app.demo.demot3h.R;

public class DemoAsyntaskActivity extends AppCompatActivity {
    GridView gridView;
    Button btnNormal, btnAsyntask;
    private List<Country> countries;
    CountryAdapter countryAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_asyntask);
        btnAsyntask = (Button) findViewById(R.id.btnAsyntask);
        btnNormal = (Button) findViewById(R.id.btnNormal);
        gridView = (GridView) findViewById(R.id.gridView);

        btnAsyntask.setOnClickListener(v -> {
            //TODO: fix bug
            initData();
            //execute chi dc goi 1 lan ma thoi
            asyncTask.execute("country1","country2","country3","country4",
                    "country5","country6","country7","country8","country9");
//            String s[] = new String[10];
//            asyncTask.execute(s);
        });

        btnNormal.setOnClickListener(v -> {
            //15 tam hinh
            //load trong thoi gian 15s
            //sau 15s moi thay dc du lieu
            List<Country> countries =new ArrayList<Country>();
            for (int i = 0; i < 15; i++) {
                try {
                    Thread.sleep(1000);
                    countries.add(new Country(R.drawable.vietnam, "vietnam - " + i));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            CountryAdapter adapter = new CountryAdapter(this, R.layout.item_country, countries);
            gridView.setAdapter(adapter);
        });
//        CountryAdapter adapter = new CountryAdapter(this, R.layout.item_country, )
    }

    void initData(){
        countries = new ArrayList<>();
        countryAdapter = new CountryAdapter(this, R.layout.item_country, countries);
        gridView.setAdapter(countryAdapter);
    }

    AsyncTask<String, Country, Void> asyncTask =
            new AsyncTask<String, Country, Void>() {
        @Override
        protected Void doInBackground(String... params) {
            for (int i = 0; i < params.length; i++) {
                try {
                    Thread.sleep(1000);//gia lay thoi gian download file tu server
                    //thong bao cho onProgressUpdate de cap nhat ui

                    //luu file xuống bộ nhớ...
//                    Country country = new Country("path_image", "country_name");
                    Country country = new Country(R.drawable.vietnam, "vietnam - " + i);
                    publishProgress(country, country, country);//giả sử path_image la duong dan luu tru file hinh da dc download

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Country... values) {
            super.onProgressUpdate(values);
            //add country vao list countries
            countries.add(values[0]); //????
            countryAdapter.notifyDataSetChanged();

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(DemoAsyntaskActivity.this, "DONE", Toast.LENGTH_SHORT).show();
        }
    };
}
