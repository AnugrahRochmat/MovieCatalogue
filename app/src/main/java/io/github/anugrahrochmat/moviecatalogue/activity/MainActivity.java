package io.github.anugrahrochmat.moviecatalogue.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import io.github.anugrahrochmat.moviecatalogue.R;
import io.github.anugrahrochmat.moviecatalogue.fragment.TabsLayoutFragment;

public class MainActivity extends AppCompatActivity {

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHandler = new Handler();

        if (savedInstanceState == null) {
            loadFragment();
        }
    }

    public void loadFragment(){
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

//                FindMoviesFragment findMoviesFragment = new FindMoviesFragment();
//                MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
//                MoviesTabsFragment moviesTabsFragment = MoviesTabsFragment.newInstance(SORT_BY_NOW_PLAYING);
                TabsLayoutFragment tabsLayoutFragment = new TabsLayoutFragment();

                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame_container, tabsLayoutFragment);
                fragmentTransaction.commit();
            }
        };

        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }
    }
}
