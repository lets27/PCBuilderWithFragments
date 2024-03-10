package com.example.pcbuilder.MyViews.FragmentsAdapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pcbuilder.Models.Product;
import com.example.pcbuilder.MyViews.EditActivity;
import com.example.pcbuilder.MyViews.ItemDetails;
import com.example.pcbuilder.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {
    Context context;
        List<Product>items;
    public RecyclerAdapter(Context context,List<Product>items) {
        this.context = context;
        this.items=items;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.newitemview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(items.get(position).getImage()).into(holder.itemPicture);
        holder.itemName.setText(items.get(position).getItemName());
        holder.price.setText(items.get(position).getPrice());



        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, EditActivity.class);
                intent.putExtra("image", items.get(holder.getAdapterPosition()).getImage());
                intent.putExtra("description", items.get(holder.getAdapterPosition()).getDescription());
                intent.putExtra("itemName", items.get(holder.getAdapterPosition()).getItemName());
                intent.putExtra("price", items.get(holder.getAdapterPosition()).getPrice());
                intent.putExtra("key", items.get(holder.getAdapterPosition()).getKey());

                context.startActivity(intent);
            }

        });
        holder.itemPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ItemDetails.class);
                intent.putExtra("image",items.get(holder.getAdapterPosition()).getImage());
                intent.putExtra("description", items.get(holder.getAdapterPosition()).getDescription());
                intent.putExtra("itemName", items.get(holder.getAdapterPosition()).getItemName());
                intent.putExtra("price", items.get(holder.getAdapterPosition()).getPrice());
                intent.putExtra("key", items.get(holder.getAdapterPosition()).getKey());
                context.startActivity(intent);
            }
        });


        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        handleDeleteAction(holder.getAdapterPosition());
                handleDeleteAction(holder.getAdapterPosition());
            }
        });
    }



    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }



    private void handleDeleteAction(int position) {
        Product productToDelete = items.get(position);

        // Delete the item from Firebase and update the RecyclerView
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Shopping Items");
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReferenceFromUrl(productToDelete.getImage());

        storageReference.delete().addOnSuccessListener(unused -> {
            reference.child(productToDelete.getKey()).removeValue();
            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
            // Remove the item from the list and notify the adapter
            items.remove(position);
            notifyItemRemoved(position);
        }).addOnFailureListener(e -> {
            // Handle failure (e.g., show an error toast)
           // Toast.makeText(context, "Deletion failed", Toast.LENGTH_SHORT).show();
        });
    }
}


