package dzumi.demo.app.demoapiservice.network;

import android.content.Context;

import java.io.IOException;

import dzumi.demo.app.demoapiservice.model.response.BaseResponse;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Dzumi on 7/24/2016.
 */
public class NetworkRequest {
    public static <T> Subscription performAsyncRequest(final Context context,
                                                       Observable<T> observable,
                                                       Func1<? super T, BaseResponse> onDoInBackground,
                                                       Action1<BaseResponse> onNext) {
        // Specify a scheduler (Scheduler.newThread(), Scheduler.immediate(), ...)
        // We choose Scheduler.io() to perform network request in a thread pool
        return observable.subscribeOn(Schedulers.io())
                // Observe result in the main thread to be able to update UI
                .observeOn(AndroidSchedulers.mainThread())
                // Set callbacks actions
                .map(onDoInBackground)
                .onErrorReturn(throwable -> {
                    BaseResponse baseResponse = new BaseResponse();
                    android.util.Log.i("errorNetwork", throwable.getMessage());
                    if (throwable instanceof HttpException) {
                        HttpException response = (HttpException) throwable;
                        int code = response.code();
                        baseResponse.setStatus(code);
                        baseResponse.setMessage(response.getMessage());

                    }else {
                        baseResponse.setMessage(throwable.getMessage());
                        baseResponse.setStatus(0);
                    }
                    return baseResponse;
                })
                .subscribe(onNext);
    }
}
