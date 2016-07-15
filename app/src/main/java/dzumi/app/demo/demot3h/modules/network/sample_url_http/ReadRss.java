package dzumi.app.demo.demot3h.modules.network.sample_url_http;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import dzumi.app.demo.demot3h.R;

public class ReadRss extends AppCompatActivity {

    private static final String TAG = "ReadRss";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_rss);
        try {
            new MyAsyncTask().execute(new URL("http://vnexpress.net/rss/tin-moi-nhat.rss"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    class MyAsyncTask extends AsyncTask<URL, Void, String>{
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
                    //parse xml
                    List<News> newsList = parseRSSVnExpress(inputStream);

                    //test parse
                    for(News news : newsList)
                        Log.i(TAG, "doInBackground: " + news.toString());


                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            //TODO: xuat gia tri tra ve len textView
            return total.toString();
        }
    };

    public List<News> parseRSSVnExpress(InputStream inputStream){
        List<News> newsList = new ArrayList<>();
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(inputStream);
            if(document != null) {
                NodeList items = document.getElementsByTagName("item");
                if(items != null) {

                    for (int i = 0; i < items.getLength(); i++) {
                        News news = new News();
                        Node item = items.item(i);
                        NodeList children = item.getChildNodes();
                        for (int j = 0; j < children.getLength(); j++) {
                            Node node = children.item(j);
                            String content = node.getTextContent();
                            switch (node.getNodeName()){
                                case News.TITLE:
                                    news.setTitle(content);
                                    break;
                                case News.PUB_DATE:
                                    news.setPubDate(content);
                                    break;
                                case News.DESCRIPTION:
//                                    news.setTitle(content);
                                    break;
                                case News.LINK:
                                    news.setLink(content);
                                    break;
                            }
                        }

                        newsList.add(news);
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return newsList;
    }
}
