package io.github.anugrahrochmat.moviecatalogue.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.anugrahrochmat.moviecatalogue.R;
import io.github.anugrahrochmat.moviecatalogue.fragment.AboutFragment;
import io.github.anugrahrochmat.moviecatalogue.fragment.FindMoviesFragment;
import io.github.anugrahrochmat.moviecatalogue.fragment.TabsLayoutFragment;
import io.github.anugrahrochmat.moviecatalogue.other.DrawerLocker;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DrawerLocker {

    public static final String BACK_STACK_ROOT_TAG = "root_fragment";

    @BindView(R.id.drawer_layout)   DrawerLayout drawerLayout;
    @BindView(R.id.nav_view)        NavigationView navigationView;
    @BindView(R.id.toolbar)         Toolbar toolbar;

    private Handler mHandler;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        toolbar.setTitle(R.string.app_name);
        navigationView.setNavigationItemSelectedListener(this);

        mHandler = new Handler();

        if (savedInstanceState == null) {
            loadFragment();
        }

        /**
         * Stetho
         */
//        Stetho.initializeWithDefaults(this);
//        Stetho.InitializerBuilder initializerBuilder = Stetho.newInitializerBuilder(this);
//        initializerBuilder.enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this));
//        Stetho.initialize(initializerBuilder.build());
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
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        };

        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        item.setChecked(true);
        drawerLayout.closeDrawers();

        Fragment fragment = null;
        Class fragmentClass = null;

        if (id == R.id.nav_movies) {
            fragmentClass = TabsLayoutFragment.class;
            toolbar.setTitle(R.string.app_name);
        } else if (id == R.id.nav_discover) {
            fragmentClass = FindMoviesFragment.class;
            toolbar.setTitle(R.string.nav_discover);
        } else if (id == R.id.nav_about) {
            fragmentClass = AboutFragment.class;
            toolbar.setTitle(R.string.nav_about_us);
        } else if (id == R.id.nav_setting) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            drawerLayout.closeDrawers();
            return true;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.frame_container, fragment);
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void lockDrawer() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        toggle.onDrawerStateChanged(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.syncState();
        toolbar.setVisibility(View.GONE);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
    }

    @Override
    public void unlockDrawer() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        toggle.onDrawerStateChanged(DrawerLayout.LOCK_MODE_UNLOCKED);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
        toolbar.setVisibility(View.VISIBLE);
    }
}

