package com.gmedia.designgtv;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.gmedia.designgtv.fragment.KidsFragment;
import com.gmedia.designgtv.fragment.MovieFragment;
import com.gmedia.designgtv.fragment.MusicFragment;
import com.gmedia.designgtv.fragment.NewsFragment;
import com.gmedia.designgtv.fragment.SportFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {

  @StringRes
  private static final int[] TAB_TITLES = new int[] { R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3, R.string.tab_text_4, R.string.tab_text_5 };
  private final Context mContext;

  public TabsPagerAdapter(Context context, FragmentManager fm) {
      super(fm);
      mContext = context;
  }

  @Override
  public Fragment getItem(int position) {
    switch (position) {
      case 0:
        return MovieFragment.newInstance();
      case 1:
        return MusicFragment.newInstance();
      case 2:
        return SportFragment.newInstance();
      case 3:
        return KidsFragment.newInstance();
      case 4:
        return NewsFragment.newInstance();
      default:
        return null;
    }
  }

  @Nullable
  @Override
  public CharSequence getPageTitle(int position) {
    return mContext.getResources().getString(TAB_TITLES[position]);
  }

  @Override
  public int getCount() {
    // Show 3 total pages.
    return 5;
  }
}