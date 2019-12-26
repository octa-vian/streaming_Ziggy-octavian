package com.gmedia.designgtv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.gmedia.designgtv.model.KategoriModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private RecyclerView recyclerView = null;

    private List<KategoriModel> itemList = null;

    private KategoriAdapter customRecyclerViewDataAdapter = null;

    private Handler uiHandler = null;

    Context context;

    private int MESSAGE_UPDATE_RECYCLER_VIEW = 1;

    private String MESSAGE_KEY_NEW_ITEM_INDEX = "MESSAGE_KEY_NEW_ITEM_INDEX";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rv_kategori);
        GridLayoutManager layoutManager = new GridLayoutManager(this,1);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerView.setLayoutManager(layoutManager);

        customRecyclerViewDataAdapter = new KategoriAdapter(context,itemList);
        recyclerView.setAdapter(customRecyclerViewDataAdapter);

        recyclerView.scrollToPosition(1);

//        TabsPagerAdapter tabsPagerAdapter = new TabsPagerAdapter(this, getSupportFragmentManager());
//
//        ViewPager viewPager = findViewById(R.id.view_pager);
//        viewPager.setAdapter(tabsPagerAdapter);
//
//        TabLayout tabs = findViewById(R.id.tabs);
//        tabs.setupWithViewPager(viewPager);
//        tabs.getTabAt(1).select();
//
//        tabs.setSelectedTabIndicatorColor(Color.parseColor("#FFC82A"));
//        tabs.setSelectedTabIndicatorHeight((int) (1 * getResources().getDisplayMetrics().density));
////        tabs.setTabTextColors(Color.parseColor("#FFC82A"), Color.parseColor("#FFC82A"));

    }

}
