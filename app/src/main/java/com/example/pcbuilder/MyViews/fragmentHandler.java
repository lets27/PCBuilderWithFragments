package com.example.pcbuilder.MyViews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.pcbuilder.MyViews.FragmentsAdapters.FragAdapter;
import com.example.pcbuilder.R;
import com.google.android.material.tabs.TabLayout;

public class fragmentHandler extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_interface);

        FragAdapter fragAdapter = new FragAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        fragAdapter.addFragments(new productListFragment(),"all products");
        fragAdapter.addFragments(new PurchasedListFragment(),"purchased");
        // Add more fragments as needed

        tabLayout=findViewById(R.id.tabLayout);
        viewPager=findViewById(R.id.viewPager);
        viewPager.setAdapter(fragAdapter);

        // Set the offscreen page limit to 0
        viewPager.setOffscreenPageLimit(0);

        // Connect the TabLayout with the ViewPager
        tabLayout.setupWithViewPager(viewPager);
    }
}
