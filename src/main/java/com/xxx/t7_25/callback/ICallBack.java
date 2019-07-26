package com.xxx.t7_25.callback;

import com.xxx.t7_25.bean.FoodBean;

public interface ICallBack {
    void onSuccess(FoodBean foodBean);

    void onfailed(String error);
}
