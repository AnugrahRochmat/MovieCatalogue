package io.github.anugrahrochmat.moviecatalogue.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import io.github.anugrahrochmat.moviecatalogue.R;
import io.github.anugrahrochmat.moviecatalogue.fragment.MoviesTabsFragment;

public class TabsViewPagerAdapter extends FragmentPagerAdapter {

    private final String SORT_BY_UPCOMING = "upcoming";
    private final String SORT_BY_NOW_PLAYING = "now_playing";

    private Context context;

    public TabsViewPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    // This method determine the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return MoviesTabsFragment.newInstance(SORT_BY_NOW_PLAYING);
        } else {
            return MoviesTabsFragment.newInstance(SORT_BY_UPCOMING);
        }
    }

    // This method determine the number of tab
    @Override
    public int getCount() {
        return 2;
    }

    // This method determine the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getString(R.string.now_playing);
            case 1:
                return context.getString(R.string.upcoming);
            default:
                return null;
        }
    }
}
