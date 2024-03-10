package com.example.pcbuilder.MyViews;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pcbuilder.Models.Product;
import com.example.pcbuilder.MyViews.FragmentsAdapters.RecyclerAdapter;
import com.example.pcbuilder.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class productListFragment extends Fragment {

    private RecyclerView recyclerView;

    ValueEventListener valueEventListener;

    private FloatingActionButton addButton;


    private DatabaseReference databaseReference;


    private List<Product> items;


    private RecyclerAdapter itemAdapter;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);
        recyclerView = view.findViewById(R.id.mondayRecyclerView);

        addButton = view.findViewById(R.id.floating);



        items = new ArrayList<>();
        itemAdapter = new RecyclerAdapter(getContext(), items);  // Initialize your adapter

        // Set up the RecyclerView with a GridLayoutManager
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        // Initializing the adapter and set it to the RecyclerView
        recyclerView.setAdapter(itemAdapter);

        // Initialize your DatabaseReference
        databaseReference = FirebaseDatabase.getInstance().getReference("Shopping Items");

        valueEventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                items.clear();
                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                    Product product = itemSnapshot.getValue(Product.class);
                    if (product != null) {
                        product.setKey(itemSnapshot.getKey());
                        items.add(product);
                    }
                }
                itemAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });




        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), AddItem.class));
            }
        });



        // Inflate the layout for this fragment
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // Unregister the ValueEventListener when the view is destroyed
        if (valueEventListener != null) {
            databaseReference.removeEventListener(valueEventListener);
        }
    }

    // Method to delete a product item
    private void deleteItem(int position) {
        // Check if the position is within the valid range
        if (position >= 0 && position < items.size()) {
            Product productToDelete = items.get(position);

            // Removing the item from Firebase
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Shopping Items");
            reference.child(productToDelete.getKey()).removeValue();

            // Removing the item from the list and notify the adapter
            items.remove(position);
            itemAdapter.notifyItemRemoved(position);
        }
    }


}
