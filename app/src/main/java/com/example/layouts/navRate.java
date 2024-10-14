package com.example.layouts;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.layouts.model.RatingData;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class navRate extends AppCompatActivity {

    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_nav_rate);

        ratingBar = findViewById(R.id.ratingBar);

        // Set a listener to respond to rating changes
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                // Display a toast message with the selected rating
                Toast.makeText(navRate.this, "You rated " + rating + " stars!", Toast.LENGTH_SHORT).show();

                // Optional: You can perform further actions here, such as submitting the rating to a database
            }
        });

        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve recommendation, name, and rating
                String recommendation = ((EditText) findViewById(R.id.recommendationEditText)).getText().toString();
                String name =  ((EditText) findViewById(R.id.nameEditText)).getText().toString();// You can replace this with actual user's name
                float rating = ratingBar.getRating();

                // Create data object
                RatingData ratingData = new RatingData(recommendation, name, rating);

                // Get Firebase database reference
                DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("ratings");

                // Push data to database
                databaseRef.push().setValue(ratingData);

                // Display success message
                Toast.makeText(navRate.this, "Feedback submitted successfully!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
