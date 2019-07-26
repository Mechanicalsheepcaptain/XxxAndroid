package com.xxx.t7_25.view;

import com.xxx.t7_25.bean.FoodBean;

public interface IView {
    void onSuccess(FoodBean foodBean);

    void onfailed(String error);
}
