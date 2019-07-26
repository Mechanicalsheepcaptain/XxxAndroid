package com.xxx.t7_25.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xxx.t7_25.R;
import com.xxx.t7_25.adapter.RecyAda;
import com.xxx.t7_25.bean.FoodBean;
import com.xxx.t7_25.model.ImpModel;
import com.xxx.t7_25.presenter.ImpPresenter;
import com.xxx.t7_25.view.IView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements IView {


    private ArrayList<FoodBean.DataBean> list;
    private RecyAda ada;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        RecyclerView mRecy = view.findViewById(R.id.recy);
        mRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        ImpPresenter impPresenter = new ImpPresenter(this, new ImpModel());
        impPresenter.getData();
        list = new ArrayList<>();
        ada = new RecyAda(getContext(), list);
        mRecy.setAdapter(ada);

    }



    @Override
    public void onSuccess(FoodBean foodBean) {
        List<FoodBean.DataBean> data = foodBean.getData();
        list.addAll(data);
        ada.notifyDataSetChanged();
    }

    @Override
    public void onfailed(String error) {
        Log.e("tag",error);
    }
}
