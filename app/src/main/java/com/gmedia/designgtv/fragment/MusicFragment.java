package com.gmedia.designgtv.fragment;


import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmedia.designgtv.MusicAdapter;
import com.gmedia.designgtv.MusicModel;
import com.gmedia.designgtv.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MusicFragment extends Fragment {

    private RecyclerView recyclerView;
    private MusicAdapter adapter;
    private List<MusicModel> musicList;

    public MusicFragment() {
        // Required empty public constructor
    }
    public static MusicFragment newInstance()   {
        return new MusicFragment();
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_music, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_music);

        musicList = new ArrayList<>();
        adapter = new MusicAdapter(getContext(), musicList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 4);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(4, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        prepareMusic();
        return view;
    }


    private void prepareMusic() {
        int[] covers = new int[]{
                R.drawable.bioskopindo,
                R.drawable.hooq,
                R.drawable.hulu,
                R.drawable.iflix,
                R.drawable.netflix,
                R.drawable.viu,
                R.drawable.zeebioskop,};

        MusicModel a = new MusicModel("Bioskop Indo", covers[0]);
        musicList.add(a);

        a = new MusicModel("Hooq", covers[1]);
        musicList.add(a);

        a = new MusicModel("Hulu", covers[2]);
        musicList.add(a);

        a = new MusicModel("Iflix", covers[3]);
        musicList.add(a);

        a = new MusicModel("Netflix", covers[4]);
        musicList.add(a);

        a = new MusicModel("Viu", covers[5]);
        musicList.add(a);

        a = new MusicModel("Zee Bioskop", covers[6]);
        musicList.add(a);

        adapter.notifyDataSetChanged();
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
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
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
