package io.github.anugrahrochmat.moviecatalogue.ui.tabslayout;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.anugrahrochmat.moviecatalogue.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabsLayoutFragment extends Fragment {

    @BindView(R.id.viewpager)       ViewPager viewPager;
    @BindView(R.id.sliding_tabs)    TabLayout tabLayout;

    public TabsLayoutFragment() {
        // Required empty public constructor
    }

    public static TabsLayoutFragment newInstance(){
        TabsLayoutFragment tabsLayoutFragment = new TabsLayoutFragment();
        return tabsLayoutFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tabs_container, container, false);
        ButterKnife.bind(this, view);


        // Create an adapter that knows which fragment should be shown on each page
        TabsViewPagerAdapter adapter = new TabsViewPagerAdapter(view.getContext(), getChildFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

}
