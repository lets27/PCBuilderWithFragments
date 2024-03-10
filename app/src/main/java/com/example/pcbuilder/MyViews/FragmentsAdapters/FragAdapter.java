package com.example.pcbuilder.MyViews.FragmentsAdapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class FragAdapter extends FragmentPagerAdapter{

    public final ArrayList<Fragment>fragmentArrayList=new ArrayList<>();
    public final ArrayList<String> fragmentTitle=new ArrayList<>();
    public FragAdapter(FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }
    public void addFragments(Fragment fragment,String title){
        fragmentArrayList.add(fragment);
        fragmentTitle.add(title);
    }

    public  CharSequence getPageTitle(int postiion){
        return fragmentTitle.get(postiion);
    }
}
