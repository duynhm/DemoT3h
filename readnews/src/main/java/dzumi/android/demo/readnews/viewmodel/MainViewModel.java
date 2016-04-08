package dzumi.android.demo.readnews.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.util.List;

import dzumi.android.demo.readnews.model.News;
import dzumi.android.demo.readnews.utils.XMLParser;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by Dzumi on 4/4/2016.
 */
public class MainViewModel implements ViewModel {

    Context mContext;
    DataListener mDataListener;

    public ObservableInt infoMessageVisibility;
    public ObservableInt progressVisibility;
    public ObservableInt recyclerVisibility;
    public ObservableField<String> infoMessage;

    private Subscription mSubscription;

    public MainViewModel(Context context, DataListener dataListener) {
        mContext = context;
        mDataListener = dataListener;

        infoMessageVisibility = new ObservableInt(View.VISIBLE);
        progressVisibility = new ObservableInt(View.INVISIBLE);
        recyclerVisibility = new ObservableInt(View.INVISIBLE);
        infoMessage = new ObservableField<>("Chưa có dữ liệu");
    }

    @Override
    public void destroy() {
        if (mSubscription != null && !mSubscription.isUnsubscribed())
            mSubscription.unsubscribe();
        mSubscription = null;
        mContext = null;
        mDataListener = null;
    }

    public interface DataListener {
        void onNewsChanged(List<News> newsList);
    }

    void loadNews() {
        progressVisibility.set(View.VISIBLE);
        recyclerVisibility.set(View.INVISIBLE);
        infoMessageVisibility.set(View.INVISIBLE);
        if (mSubscription != null && !mSubscription.isUnsubscribed())
            mSubscription.unsubscribe();

    }

    public Observable<List<News>> getNews() {
        try {
//            String content = run("http://vnexpress.net/rss/tin-moi-nhat.rss");
//            Log.d("duynhm", content);
            run("http://vnexpress.net/rss/tin-moi-nhat.rss");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    void run(){
        Observable.create(new Observable.OnSubscribe<List<News>>() {
            OkHttpClient okHttpClient = new OkHttpClient();
            @Override
            public void call(Subscriber<? super List<News>> subscriber) {
                try {
                    Response response = okHttpClient.newCall(new Request.Builder().url("").build()).execute();
//                    subscriber.onNext(response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    void run(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }
                String content = response.body().string();
                XMLParser.getNewsByVnExpress(content);
                Log.d("duynhm", content);
            }
        });
    }
}
