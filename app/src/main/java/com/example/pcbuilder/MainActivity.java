package com.example.pcbuilder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.pcbuilder.Models.Product;
import com.example.pcbuilder.MyViews.FragmentsAdapters.FragAdapter;
import com.example.pcbuilder.MyViews.productListFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ValueEventListener valueEventListener;

    private RecyclerView recyclerView;


    private FloatingActionButton addButton;


    private DatabaseReference databaseReference;


    private List<Product> productList;


    private FragAdapter adapter;

    private TabLayout tabLayout;
    private ViewPager viewPager;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout=findViewById(R.id.tabLayout);
        viewPager=findViewById(R.id.viewPager);


        tabLayout.setupWithViewPager(viewPager);
        adapter=new FragAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.addFragments(new productListFragment(),"products");
         adapter.addFragments(new productListFragment(),"purchased");

        tabLayout=findViewById(R.id.tabLayout);
        viewPager=findViewById(R.id.viewPager);


        // Set the offscreen page limit to 0
        viewPager.setOffscreenPageLimit(0);

        // Connect the TabLayout with the ViewPager
        tabLayout.setupWithViewPager(viewPager);

        viewPager.setAdapter(adapter);

    }


}