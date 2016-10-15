package dzumi.app.demo.demot3h.modules.network.sample_url_http;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

    void initRecyclerView(List<News> list){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        NewsAdapter newsAdapter = new NewsAdapter(this, R.layout.item_rss, list);
        recyclerView.setAdapter(newsAdapter);
        //day la recycler view dang list
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    class MyAsyncTask extends AsyncTask<URL, Void, List<News>>{
        @Override
        protected List<News> doInBackground(URL... params) {
            URLConnection urlConnection = null;
            List<News> newsList = new ArrayList<>();
            try {
                urlConnection = params[0].openConnection();
                HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
                int respCode = httpURLConnection.getResponseCode();
                if(respCode == HttpURLConnection.HTTP_OK){
                    InputStream inputStream = httpURLConnection.getInputStream();
                    //parse xml
                    newsList = parseRSSVnExpress(inputStream);

                    //test parse
                   /* for(News news : newsList)
                        Log.i(TAG, "doInBackground: " + news.toString());*/


                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            //TODO: xuat gia tri tra ve len textView
            return newsList;
        }

        @Override
        protected void onPostExecute(List<News> newsList) {
            super.onPostExecute(newsList );
            initRecyclerView(newsList);
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
                                    //description bao gồm: image & text
                                    //cắt chuỗi dựa vào các dấu hiệu
                                    String temp[] = content.split("</br>");
                                    //temp[0] chua image, temp[1] chua description
                                    news.setDescription(temp[1].split("]]>")[0].trim());
                                    news.setImageLink(temp[0].split("src=\"")[1].split("\"")[0].trim());
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
