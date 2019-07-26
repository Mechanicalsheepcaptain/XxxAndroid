package com.xxx.t7_25.api;

import com.xxx.t7_25.bean.FoodBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiServer {
    String getUrl = "http://www.qubaobei.com/ios/cf/";

    @GET("dish_list.php?stage_id=1&limit=20&page=1")
    Observable<FoodBean> getData();
}
