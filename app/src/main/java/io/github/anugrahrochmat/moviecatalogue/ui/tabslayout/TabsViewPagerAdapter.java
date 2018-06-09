package io.github.anugrahrochmat.moviecatalogue.ui.tabslayout;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import io.github.anugrahrochmat.moviecatalogue.R;
import io.github.anugrahrochmat.moviecatalogue.ui.movietabs.MoviesTabsFragment;

public class TabsViewPagerAdapter extends FragmentPagerAdapter {

    private final String SORT_BY_NOW_PLAYING = "now_playing";
    private final String SORT_BY_UPCOMING = "upcoming";
    private final String SORT_BY_POPULAR = "popular";
    private final String SORT_BY_TOP_RATED = "top_rated";

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
        } else if (position == 1) {
            return MoviesTabsFragment.newInstance(SORT_BY_UPCOMING);
        } else if (position == 2){
            return MoviesTabsFragment.newInstance(SORT_BY_POPULAR);
        } else {
            return MoviesTabsFragment.newInstance(SORT_BY_TOP_RATED);
        }
    }

    // This method determine the number of tab
    @Override
    public int getCount() {
        return 4;
    }

    // This method determine the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getString(R.string.now_playing);
            case 1:
                return context.getString(R.string.upcoming);
            case 2:
                return context.getString(R.string.popular);
            case 3:
                return context.getString(R.string.top_rated);
            default:
                return null;
        }
    }
}
