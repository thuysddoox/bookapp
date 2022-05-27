package com.example.bookshop.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.bookshop.fragment.FragmentFavourite;
import com.example.bookshop.fragment.FragmentHistory;
import com.example.bookshop.fragment.FragmentHome;
import com.example.bookshop.fragment.FragmentProfile;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new FragmentHome();
            case 1: return new FragmentFavourite();
            case 2: return new FragmentHistory();
            case 3: return new FragmentProfile();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
