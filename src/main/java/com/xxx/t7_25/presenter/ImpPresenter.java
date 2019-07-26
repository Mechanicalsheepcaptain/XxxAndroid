package com.xxx.t7_25.presenter;

import com.xxx.t7_25.bean.FoodBean;
import com.xxx.t7_25.callback.ICallBack;
import com.xxx.t7_25.model.IModel;
import com.xxx.t7_25.view.IView;

public class ImpPresenter implements IPresenter, ICallBack {
    private IView iView;
    private IModel iModel;

    public ImpPresenter(IView iView, IModel iModel) {
        this.iView = iView;
        this.iModel = iModel;
    }

    @Override
    public void onSuccess(FoodBean foodBean) {
        if (iView != null) {
            iView.onSuccess(foodBean);
        }
    }

    @Override
    public void onfailed(String error) {
        if (iView != null) {
            iView.onfailed(error);
        }
    }

    @Override
    public void getData() {
        if (iModel != null) {
            iModel.getData(this);
        }
    }
}
