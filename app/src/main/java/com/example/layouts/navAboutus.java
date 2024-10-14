package com.example.layouts;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class navAboutus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_nav_aboutus);

        ImageButton facebookButton = findViewById(R.id.facebookButton);
        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open Facebook profile link
                String facebookUrl = "https://www.facebook.com/muhammad.bakhat.589/";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(facebookUrl));
                startActivity(intent);
            }
        });

// LinkedIn button click listener
        ImageButton linkedinButton = findViewById(R.id.linkedinButton);
        linkedinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open LinkedIn profile link
                String linkedinUrl = "https://www.linkedin.com/in/bakhat-nasar/";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(linkedinUrl));
                startActivity(intent);
            }
        });
    }
}