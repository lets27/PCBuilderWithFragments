package com.example.pcbuilder.MyViews.FragmentsAdapters;

import android.view.TextureView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pcbuilder.R;

public class ViewHolder extends RecyclerView.ViewHolder{
    ImageView itemPicture,deleteBtn,purchaseIcon,edit;
    TextView itemName,description,price;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        itemPicture=itemView.findViewById(R.id.picture);
        description= itemView.findViewById(R.id.description);
        itemName=itemView.findViewById(R.id.productName);
        price=itemView.findViewById(R.id.itemPrice);
        edit=itemView.findViewById(R.id.editIcon);
        deleteBtn= itemView.findViewById(R.id.deleteIcon);
        purchaseIcon=itemView.findViewById(R.id.purchasedImage);
    }

}
