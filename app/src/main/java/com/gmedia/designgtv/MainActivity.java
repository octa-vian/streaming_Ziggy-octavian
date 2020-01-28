package com.gmedia.designgtv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.gmedia.designgtv.fragment.MusicFragment;
import com.gmedia.designgtv.model.ItemModel;
import com.gmedia.designgtv.model.KategoriModel;
import com.gmedia.designgtv.utils.Url;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import co.id.gmedia.coremodul.ApiVolley;
import co.id.gmedia.coremodul.AppRequestCallback;
import co.id.gmedia.coremodul.CustomModel;
import co.id.gmedia.coremodul.DialogBox;
import co.id.gmedia.coremodul.JSONBuilder;

public class MainActivity extends AppCompatActivity implements KategoriAdapter.KategoriAdapterCallback {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private List<KategoriModel> itemList = new ArrayList<>();
    private List<ItemModel> itemModels = new ArrayList<>();
    private KategoriAdapter adapter;
    private ItemAdapter itemAdapter;
    Context context;
    private DialogBox dialogBox,dialogItem;
    String kategori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_menu);
        context = this;
        dialogBox = new DialogBox(this);
        dialogItem = new DialogBox(this);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rv_kategori);

        adapter = new KategoriAdapter(context,itemList,this);
        GridLayoutManager layoutManager = new GridLayoutManager(this,1);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        initData();

        // item tv
        RecyclerView rv_item = (RecyclerView) findViewById(R.id.rv_item);
        itemAdapter = new ItemAdapter(this, itemModels);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 4);
        rv_item.setLayoutManager(mLayoutManager);
        rv_item.addItemDecoration(new GridSpacingItemDecoration(4, dpToPx(10), true));
        rv_item.setItemAnimator(new DefaultItemAnimator());
        rv_item.setAdapter(itemAdapter);
        initItemTv();
//        prepareItem();
    }

//    private void prepareItem() {
//        String[] covers = new String[]{
//                "http://gmedia.bz/gmediatv/img/bioskopindo.png",
//                "http://gmedia.bz/gmediatv/img/hooq.png",
//                "http://gmedia.bz/gmediatv/img/hulu.png",
//                "http://gmedia.bz/gmediatv/img/iflix.png",
//                "http://gmedia.bz/gmediatv/img/netflix.png",
//                "http://gmedia.bz/gmediatv/img/viu.png",
//                "http://gmedia.bz/gmediatv/img/zeebioskop.png"};
//
//        ItemModel a = new ItemModel("1", covers[0]);
//        itemModels.add(a);
//
//        a = new ItemModel("2", covers[1]);
//        itemModels.add(a);
//
//        a = new ItemModel("3", covers[2]);
//        itemModels.add(a);
//
//        a = new ItemModel("4", covers[3]);
//        itemModels.add(a);
//
//        a = new ItemModel("5", covers[4]);
//        itemModels.add(a);
//
//        a = new ItemModel("6", covers[5]);
//        itemModels.add(a);
//
//        a = new ItemModel("7", covers[6]);
//        itemModels.add(a);
//
//        adapter.notifyDataSetChanged();
//    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initData() {
        dialogBox.showDialog(false);
        JSONObject jBody = new JSONObject();

        new ApiVolley(context, jBody, "GET", Url.getKategori,
                new AppRequestCallback(new AppRequestCallback.ResponseListener() {
                    @Override
                    public void onSuccess(String response, String message) {
                        dialogBox.dismissDialog();
                        itemList.clear();
                        try{
                            JSONArray obj = new JSONArray(response);
                            Log.d(LOG_TAG,">>>>"+obj);
                            for(int i = 0; i < obj.length(); i++){
                                JSONObject jadwal = obj.getJSONObject(i);
                                KategoriModel movie = new KategoriModel(
                                                jadwal.getString("id")
                                                ,jadwal.getString("kategori")
                                        );
                                itemList.add(movie);
                            }
                            adapter.notifyDataSetChanged();
                        }
                        catch (JSONException e){
                            dialogBox.dismissDialog();
                            View.OnClickListener clickListener = new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    dialogBox.dismissDialog();
                                    initData();
                                }
                            };

                            dialogBox.showDialog(clickListener, "Ulangi Proses", "Terjadi kesalahan saat mengambil data");
                        }
                    }

                    @Override
                    public void onEmpty(String message) {

                        itemList.clear();
                        adapter.notifyDataSetChanged();

                        dialogBox.dismissDialog();
                    }

                    @Override
                    public void onFail(String message) {
                        dialogBox.dismissDialog();
                        View.OnClickListener clickListener = new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                dialogBox.dismissDialog();
                                initData();

                            }
                        };

                        dialogBox.showDialog(clickListener, "Ulangi Proses", "Terjadi kesalahan saat mengambil data");
                    }
                })
        );
    }

    @Override
    public void onRowKategoriCallback(String id_kategori) {
        kategori = id_kategori;
//        Toast.makeText(getApplicationContext(),id_kategori,Toast.LENGTH_SHORT).show();
        initItemTv();
    }

    private void initItemTv(){
        dialogItem.showDialog(false);
        JSONObject jBody = new JSONObject();
        try {
            jBody.put("kategori",kategori);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        new ApiVolley(context, jBody, "POST", Url.getItemTV,
                new AppRequestCallback(new AppRequestCallback.ResponseListener() {
                    @Override
                    public void onSuccess(String response, String message) {
                        dialogItem.dismissDialog();
                        itemModels.clear();
                        try{
                            JSONArray obj = new JSONArray(response);
                            Log.d(LOG_TAG,">>>>"+obj);
                            for(int i = 0; i < obj.length(); i++){
                                JSONObject jadwal = obj.getJSONObject(i);
                                ItemModel m = new ItemModel(
                                        jadwal.getString("id")
                                        ,jadwal.getString("title")
                                        ,jadwal.getString("icon")
                                        ,jadwal.getString("url")
                                        ,jadwal.getString("kategori")
                                        ,jadwal.getString("package")
                                        ,jadwal.getString("url_playstore")
                                        ,jadwal.getString("url_web")
                                );
                                itemModels.add(m);
                            }
                            itemAdapter.notifyDataSetChanged();
                        }
                        catch (JSONException e){
                            dialogItem.dismissDialog();
                            View.OnClickListener clickListener = new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    itemModels.clear();
                                    dialogItem.dismissDialog();
                                    initItemTv();
                                }
                            };

                            dialogItem.showDialog(clickListener, "Ulangi Proses", "Terjadi kesalahan saat mengambil data");
                        }
                    }

                    @Override
                    public void onEmpty(String message) {

                        itemModels.clear();
                        itemAdapter.notifyDataSetChanged();

                        dialogItem.dismissDialog();
                    }

                    @Override
                    public void onFail(String message) {
                        dialogItem.dismissDialog();
                        View.OnClickListener clickListener = new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogItem.dismissDialog();
                                initItemTv();
                                itemModels.clear();

                            }
                        };
                        dialogItem.showDialog(clickListener, "Ulangi Proses", "Terjadi kesalahan saat mengambil data");
                    }
                })
        );
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);
            int column = position % spanCount;

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount;
                outRect.right = (column + 1) * spacing / spanCount;

                if (position < spanCount) {
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom

            } else {
                outRect.left = column * spacing / spanCount;
                outRect.right = spacing - (column + 1) * spacing / spanCount;
                if (position >= spanCount) {
                    outRect.top = spacing;
                }
            }
        }
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
