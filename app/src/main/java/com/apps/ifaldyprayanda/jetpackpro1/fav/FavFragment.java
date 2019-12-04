package com.apps.ifaldyprayanda.jetpackpro1.fav;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apps.ifaldyprayanda.jetpackpro1.R;
import com.apps.ifaldyprayanda.jetpackpro1.fav.movie.FavMovieFragment;
import com.apps.ifaldyprayanda.jetpackpro1.fav.tvshow.FavTvShowFragment;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;
    View view;

    public FavFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance()
    {
        return new FavFragment();
    }

    private class sliderAdapter extends FragmentPagerAdapter{
        String favMovie = getResources().getString(R.string.movie);
        String favTv = getResources().getString(R.string.tv_show);

        final String tabs[] = {favMovie, favTv};


        public sliderAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position)
            {
                case 0:
                    return new FavMovieFragment();
                case 1:
                    return new FavTvShowFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return tabs.length;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager = view.findViewById(R.id.viewpager);

        viewPager.setAdapter(new sliderAdapter(getChildFragmentManager()));

        tabLayout = view.findViewById(R.id.tab);
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
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
