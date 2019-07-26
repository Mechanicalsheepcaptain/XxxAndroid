package com.xxx.t7_25.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.xxx.t7_25.R;
import com.xxx.t7_25.bean.FoodBean;

import java.util.ArrayList;

public class RecyAda extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<FoodBean.DataBean> list;
    public RecyAda(Context context, ArrayList<FoodBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==0){
            View view = View.inflate(context, R.layout.layout_recy_item1, null);
            return new MyHolder1(view);
        }else {
            View view = View.inflate(context, R.layout.layout_recy_item2, null);
            return new MyHolder2(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        if (type == 0){
            MyHolder1 myHolder1 = (MyHolder1) holder;
            myHolder1.tv.setText(list.get(position).getTitle());
            Glide.with(context).load(list.get(position).getPic()).into(myHolder1.iv);
        }else {
            MyHolder2 myHolder2 = (MyHolder2) holder;
            myHolder2.tv.setText(list.get(position).getTitle());
            Glide.with(context).load(list.get(position).getPic()).into(myHolder2.iv);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position<=5){
            return 0;
        }else {
            return 1;
        }
    }

    public class MyHolder1 extends RecyclerView.ViewHolder {

        private ImageView iv;
        private TextView tv;

        public MyHolder1(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.item1_iv);
            tv = itemView.findViewById(R.id.item1_tv);
        }
    }
    public class MyHolder2 extends RecyclerView.ViewHolder {

        private ImageView iv;
        private TextView tv;

        public MyHolder2(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.item2_iv);
            tv = itemView.findViewById(R.id.item2_tv);
        }
    }
}
