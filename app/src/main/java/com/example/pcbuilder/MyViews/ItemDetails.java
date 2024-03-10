package com.example.pcbuilder.MyViews;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.pcbuilder.R;

public class ItemDetails extends AppCompatActivity {

    // Views to display item details
    private TextView names, prices, descrip;
    private ImageView snapper;

    // Buttons for actions
    private Button delete, edit;

    // String variables to store item details
    private String key = "";
    private String imageUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        // Initialize views
        names = findViewById(R.id.gotName);
        prices = findViewById(R.id.gotPrice);
        descrip = findViewById(R.id.description);
        snapper = findViewById(R.id.detailImage);

        // Retrieve item details from the intent's extras
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            // Load and display item image using Glide
            Glide.with(this).load(bundle.getString("image")).into(snapper);

            // Set item name, price, and description
            names.setText(bundle.getString("itemName"));
            prices.setText(bundle.getString("price"));
            descrip.setText(bundle.getString("description"));

            // Store item key and image URL for potential actions
            key = bundle.getString("key");
            imageUrl = bundle.getString("image");
        }
    }

}
