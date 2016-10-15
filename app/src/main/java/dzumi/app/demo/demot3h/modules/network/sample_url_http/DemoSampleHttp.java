package dzumi.app.demo.demot3h.modules.network.sample_url_http;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import dzumi.app.demo.demot3h.R;

public class DemoSampleHttp extends AppCompatActivity {
    Button btnLoad;
    AsyncTask<URL, Void, String> asyncTask = new AsyncTask<URL, Void, String>() {
        @Override
        protected String doInBackground(URL... params) {
            URLConnection urlConnection = null;
            StringBuilder total = new StringBuilder();
            try {
                urlConnection = params[0].openConnection();
                HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
                int respCode = httpURLConnection.getResponseCode();
                if(respCode == HttpURLConnection.HTTP_OK){
                    InputStream inputStream = httpURLConnection.getInputStream();
                    //inputstream --> string --> log
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        total.append(line).append('\n');
                    }
                    Log.d("RSS", "rss:" + total);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            //TODO: xuat gia tri tra ve len textView
            return total.toString();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_sample_http);
        btnLoad = (Button) findViewById(R.id.btnLoad);
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    URL url = new URL("http://vnexpress.net/rss/tin-moi-nhat.rss");
                    //TODO:
                    asyncTask.execute(url);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
