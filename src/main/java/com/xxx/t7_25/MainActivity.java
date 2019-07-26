package com.xxx.t7_25;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.xxx.t7_25.adapter.VpAdapter;
import com.xxx.t7_25.fragment.HomeFragment;
import com.xxx.t7_25.fragment.SecondFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTab;
    private Toolbar mTool;
    private FragmentManager supportFragmentManager;
    private SecondFragment secondFragment;
    private HomeFragment homeFragment;
    private ViewPager mVp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mTab = (TabLayout) findViewById(R.id.tab);
        mTool = (Toolbar) findViewById(R.id.tool);
        mVp = (ViewPager) findViewById(R.id.vp);
        setSupportActionBar(mTool);


        supportFragmentManager = getSupportFragmentManager();
        homeFragment = new HomeFragment();
        secondFragment = new SecondFragment();
        ArrayList<Fragment> list = new ArrayList<>();
        list.add(homeFragment);
        list.add(secondFragment);
        VpAdapter adapter = new VpAdapter(getSupportFragmentManager(), list);
        mVp.setAdapter(adapter);
        mTab.setupWithViewPager(mVp);
        mTab.getTabAt(0).setText("首页");
        mTab.getTabAt(1).setText("上传");

        mTab.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position == 0) {
                    mTool.setTitle("首页");
                } else {
                    mTool.setTitle("上传");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}
