package com.xxx.t7_25.model;

import com.xxx.t7_25.api.ApiServer;
import com.xxx.t7_25.bean.FoodBean;
import com.xxx.t7_25.callback.ICallBack;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImpModel implements IModel {
    @Override
    public void getData(final ICallBack iCallBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiServer.getUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        ApiServer apiServer = retrofit.create(ApiServer.class);

        apiServer.getData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FoodBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FoodBean value) {
                        iCallBack.onSuccess(value);

                    }

                    @Override
                    public void onError(Throwable e) {
                        iCallBack.onfailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
