package com.example.layouts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.layouts.databinding.ActivityChooseLocationBinding;
import com.example.layouts.model.UserModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChooseLocationActivity extends AppCompatActivity {
    private ActivityChooseLocationBinding binding;
    private Button button;
    private String selectedLocation;
    private DatabaseReference databaseReference;
    private String userId;
    private String email;
    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChooseLocationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        EdgeToEdge.enable(this);

        // Retrieve user data from intent
        userId = getIntent().getStringExtra("userId");
        email = getIntent().getStringExtra("email");
        username = getIntent().getStringExtra("username");
        password = getIntent().getStringExtra("password");

        // Initialize Firebase Database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("user");

        String[] locationList = {"Attar Hostel", "Zikriya Hostel", "Ghazali Hostel", "Rumi Hostel", "Liaquat Hostel", "Hajveri Hostel", "Beruni Hostel"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, locationList);
        binding.listOfLocation.setAdapter(adapter);

        binding.listOfLocation.setOnItemClickListener((parent, view, position, id) -> {
            selectedLocation = locationList[position];
            Toast.makeText(ChooseLocationActivity.this, "Selected: " + selectedLocation, Toast.LENGTH_SHORT).show();
        });

        button = findViewById(R.id.button4);
        button.setOnClickListener(v -> {
            if (selectedLocation != null) {
                // Save user data with location
                saveUserToFirebase(userId, username, email,password, selectedLocation);

                // Navigate to MainActivity
                Intent intent = new Intent(ChooseLocationActivity.this, MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(ChooseLocationActivity.this, "Please select a location first.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void saveUserToFirebase(String userId, String username, String email,String password, String location) {
        UserModel user = new UserModel(username, email, password, location);
        databaseReference.child(userId).setValue(user)
                .addOnSuccessListener(aVoid -> Toast.makeText(ChooseLocationActivity.this, "User saved successfully.", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(ChooseLocationActivity.this, "Failed to save user.", Toast.LENGTH_SHORT).show());
    }
}
