package com.doyou.rxjava;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.doyou.rxjava.network.Api;

import java.util.concurrent.TimeUnit;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView text;
    Button stop;
    Disposable mDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.text);
        stop = findViewById(R.id.stop);

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDisposable != null) {
                    mDisposable.dispose();
                }
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
//        api.getRepos("faith-hb")
//                .subscribeOn(Schedulers.io()) // 网络需要在子线程中进行
//                .observeOn(AndroidSchedulers.mainThread()) // 更新UI需要在主线程
//                .subscribe(new SingleObserver<List<Repo>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        mDisposable = d;
//                    }
//
//                    @Override
//                    public void onSuccess(List<Repo> repos) {
//                        text.setText(repos.get(0).name);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        ObjectHelper.requireNonNull(e, "e not empty");
//                        String msg = e.getMessage();
//                        text.setText(msg);
//                    }
//                });

//        Single.just(1)
//                .map(new Function<Integer, String>() {
//                    @Override
//                    public String apply(Integer integer) {
//                        return String.valueOf(integer);
//                    }
//                })
//                .subscribe(new SingleObserver<String>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onSuccess(String s) {
//                        text.setText(s);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//                });

        Observable.interval(1, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                mDisposable = d;
            }

            @Override
            public void onNext(Long aLong) {
                text.setText(String.valueOf(aLong));
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }
}
